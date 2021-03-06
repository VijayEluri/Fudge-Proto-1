/* Copyright 2009 by OpenGamma Inc and other contributors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.fudgemsg.proto;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.fudgemsg.wire.types.FudgeWireType;

/**
 * Semantic representation of a message.
 * 
 * @author Andrew
 */
public abstract class MessageDefinition extends Definition {
  
  public static final MessageDefinition NULL = new NullMessageDefinition ();
  
  public static final MessageDefinition ANONYMOUS = new AnonMessageDefinition ();
  
  private static class NullMessageDefinition extends MessageDefinition {
    
    private NullMessageDefinition () {
      super (null, null, null, false);
    }
    
    @Override
    /* package */ EnumDefinition createEnumDefinition (final String identifier, final CodePosition codePosition, final boolean compilationTarget) {
      return new EnumDefinition (identifier, codePosition, null, compilationTarget);
    }
    
    @Override
    /* package */ MessageDefinition createMessageDefinition (final String identifier, final CodePosition codePosition, final boolean compilationTarget) {
      return new MessageDefinitionImpl (identifier, codePosition, null, compilationTarget);
    }
    
  }
  
  private static class MessageDefinitionImpl extends MessageDefinition {
    
    private final List<MessageDefinition> _innerMessages = new LinkedList<MessageDefinition>();
    
    private final List<FieldDefinition> _innerFields = new LinkedList<FieldDefinition>();
    
    private final List<EnumDefinition> _innerEnums = new LinkedList<EnumDefinition>();
    
    private MessageDefinitionImpl (final String identifier, final CodePosition codePosition, final MessageDefinition outerMessage, final boolean compilationTarget) {
      super (identifier, codePosition, outerMessage, compilationTarget);
    }

    @Override
    /* package */ FieldDefinition createFieldDefinition (final String identifier, final CodePosition codePosition, final FieldType type, final boolean mutable, final boolean required) {
      final FieldDefinition fieldDefinition = new FieldDefinition (identifier, codePosition, this, type, mutable, required);
      _innerFields.add (fieldDefinition);
      return fieldDefinition;
    }
    
    @Override
    /* package */ EnumDefinition createEnumDefinition (final String identifier, final CodePosition codePosition, final boolean compilationTarget) {
      final EnumDefinition enumDefinition = new EnumDefinition (identifier, codePosition, this, compilationTarget);
      _innerEnums.add (enumDefinition);
      return enumDefinition;
    }
    
    @Override
    /* package */ MessageDefinition createMessageDefinition (final String identifier, final CodePosition codePosition, final boolean compilationTarget) {
      final MessageDefinition messageDefinition = new MessageDefinitionImpl (identifier, codePosition, this, compilationTarget);
      _innerMessages.add (messageDefinition);
      return messageDefinition;
    }
    
    @Override
    public List<FieldDefinition> getFieldDefinitions () {
      return _innerFields;
    }
    
    @Override
    public List<EnumDefinition> getEnumDefinitions () {
      return _innerEnums;
    }
    
    @Override
    public List<MessageDefinition> getMessageDefinitions () {
      return _innerMessages;
    }
    
  }

  private static class AnonMessageDefinition extends MessageDefinition {
    
    private AnonMessageDefinition () {
      super ("anonymous", null, null, false);
    }
    
    public FieldType getFieldType () {
      return FieldType.AnonMessageType.INSTANCE;
    }
    
    @Override
    protected boolean hasExternalMessageReferences (Set<MessageDefinition> considered) {
      return false;
    }
    
  }
  
  private MessageDefinition _baseMessage;
  
  private boolean _abstract;
  private boolean _external;
  
  private MessageDefinition (final String identifier, final CodePosition codePosition, final MessageDefinition outerMessage, final boolean compilationTarget) {
    super (identifier, codePosition, outerMessage, compilationTarget);
  }
  
  /**
   * Returns a suitable type if this message type is used as a field (i.e. sub-message). 
   */
  public FieldType getFieldType () {
    return new FieldType.MessageType (this);
  }
  
  public MessageDefinition getOuterMessage () {
    return (MessageDefinition) getOuterDefinition ();
  }
  
  /* package */ FieldDefinition createFieldDefinition (final String identifier, final CodePosition codePosition, final FieldType type, final boolean mutable, final boolean required) {
    throw new UnsupportedOperationException ();
  }
  
  /* package */ EnumDefinition createEnumDefinition (final String identifier, final CodePosition codePosition, final boolean compilationTarget) {
    throw new UnsupportedOperationException ();
  }
  
  /* package */ MessageDefinition createMessageDefinition (final String identifier, final CodePosition codePosition, final boolean compilationTarget) {
    throw new UnsupportedOperationException ();
  }
  
  public List<FieldDefinition> getFieldDefinitions () {
    throw new UnsupportedOperationException ();
  }
  
  public FieldDefinition getFieldDefinition (final String identifier) {
    for (FieldDefinition field : getFieldDefinitions ()) {
      if (identifier.equals (field.getIdentifier ())) return field;
    }
    return null;
  }
  
  public FieldDefinition getFieldDefinition (final Integer ordinal) {
    for (FieldDefinition field : getFieldDefinitions ()) {
      if (ordinal.equals (field.getOrdinal ())) return field;
    }
    return null;
  }
  
  public List<EnumDefinition> getEnumDefinitions () {
    throw new UnsupportedOperationException ();
  }
  
  public List<MessageDefinition> getMessageDefinitions () {
    throw new UnsupportedOperationException ();
  }
  
  @Override
  public String toString () {
    final StringBuilder sb = new StringBuilder (getIdentifier ()).append (" = {");
    if (getMessageDefinitions () != null) {
      sb.append (" messages = { ");
      for (MessageDefinition message : getMessageDefinitions ()) {
        sb.append (message.toString ()).append (", ");
      }
      sb.append ('}');
    }
    if (getFieldDefinitions () != null) {
      sb.append (" fields = { ");
      for (FieldDefinition field : getFieldDefinitions ()) {
        sb.append (field.toString()).append (", ");
      }
      sb.append ('}');
    }
    if (getEnumDefinitions () != null) {
      sb.append (" enums = { ");
      for (EnumDefinition enumdef : getEnumDefinitions ()) {
        sb.append (enumdef.toString ()).append (", ");
      }
      sb.append ('}');
    }
    sb.append ("}");
    return sb.toString ();
  }
  
  public boolean extendsFrom (final MessageDefinition message) {
    if (_baseMessage == null) return false;
    if (message.equals (_baseMessage)) return true;
    return _baseMessage.extendsFrom (message);
  }
  
  public MessageDefinition getExtends () {
    return _baseMessage;
  }
  
  public void setExtends (final MessageDefinition message) {
    _baseMessage = message;
  }
  
  public boolean isExternal () {
    return _external;
  }
  
  /* package */ void setExternal () {
    _external = true;
  }
  
  public boolean isAbstract() {
    return _abstract;
  }

  /* package */void setAbstract() {
    _abstract = true;
  }

  protected boolean hasExternalMessageReferences (final FieldType type, final Set<MessageDefinition> considered) {
    if (type instanceof FieldType.MessageType) {
      return ((FieldType.MessageType)type).getMessageDefinition ().hasExternalMessageReferences (considered);
    } else if (type instanceof FieldType.ArrayType) {
      return hasExternalMessageReferences (((FieldType.ArrayType)type).getBaseType (), considered);
    } else if (type instanceof FieldType.UserType) {
      final TypeDefinition typedef = ((FieldType.UserType) type).getTypeDefinition();
      final FieldType underlying = typedef.getUnderlyingType();
      if (typedef.isExternal()) {
        return underlying.getFudgeFieldType() == FudgeWireType.SUB_MESSAGE_TYPE_ID;
      } else {
        return hasExternalMessageReferences(underlying, considered);
      }
    } else {
      return false;
    }
  }
  
  protected boolean hasExternalMessageReferences (Set<MessageDefinition> considered) {
    if (isExternal ()) return true;
    if (considered == null) considered = new HashSet<MessageDefinition> ();
    if (considered.contains (this)) return false;
    considered.add (this);
    if (getExtends () != null) {
      if (getExtends ().hasExternalMessageReferences (considered)) return true;
    }
    for (final FieldDefinition field : getFieldDefinitions ()) {
      if (hasExternalMessageReferences (field.getType (), considered)) return true;
    }
    return false;
  }

  /**
   * Returns true if this message is itself external, extends an external message, or has a field
   * which is an external message.
   */
  public boolean hasExternalMessageReferences () {
    return hasExternalMessageReferences (null);
  }

}
