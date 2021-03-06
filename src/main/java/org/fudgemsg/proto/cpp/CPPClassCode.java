/*
 * Copyright 2009 by OpenGamma Inc and other contributors.
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

package org.fudgemsg.proto.cpp;

import java.io.IOException;

import org.fudgemsg.proto.Compiler;
import org.fudgemsg.proto.EnumDefinition;
import org.fudgemsg.proto.FieldDefinition;
import org.fudgemsg.proto.IndentWriter;
import org.fudgemsg.proto.MessageDefinition;
import org.fudgemsg.proto.TaxonomyDefinition;
import org.fudgemsg.proto.c.CBlockCode;
import org.fudgemsg.proto.c.CLiteralCode;
import org.fudgemsg.proto.c.CStyleClassCode;

/**
 * Code generator for the C++ Fudge implementation
 * 
 * @author Andrew
 */
/* package */ class CPPClassCode extends CStyleClassCode {
  
  /* package */ static final CPPClassCode INSTANCE = new CPPClassCode ();
  
  private CPPClassCode () {
    super (blockCodeDelegate (new CBlockCode (literalCodeDelegate (CLiteralCode.INSTANCE))), ".H", ".CPP");
  }
  
  @Override
  public void beginClassHeaderDeclaration (final Compiler.Context context, final MessageDefinition message, final IndentWriter writer) throws IOException {
    super.beginClassHeaderDeclaration (context, message, writer);
    comment (writer, "TODO begin class header declaration");
  }
  
  @Override
  public void endClassHeaderDeclaration (final Compiler.Context context, final MessageDefinition message, final IndentWriter writer) throws IOException {
    comment (writer, "TODO end class header declaration");
  }
  
  @Override
  public void writeClassHeaderAttribute (final Compiler.Context context, final FieldDefinition field, final IndentWriter writer) throws IOException {
    comment (writer, "TODO class header attribute");
  }
  
  @Override
  public void writeClassHeaderAccessor (final Compiler.Context context, final FieldDefinition field, final IndentWriter writer) throws IOException {
    comment (writer, "TODO class header accessor");
  }
  
  @Override
  public void writeClassHeaderConstructor (final Compiler.Context context, final MessageDefinition message, final IndentWriter writer) throws IOException {
    comment (writer, "TODO class header constructor");
  }
  
  @Override
  public void writeEnumHeaderDeclaration (final Compiler.Context context, final EnumDefinition enumDefinition, final IndentWriter writer) throws IOException {
    comment (writer, "TODO enum header declaration");
  }
  
  @Override
  public void writeTaxonomyHeaderDeclaration (final Compiler.Context context, final TaxonomyDefinition taxonomyDefinition, final IndentWriter writer) throws IOException {
    comment (writer, "TODO taxonomy header declaration");
  }

  @Override
  public void beginClassImplementationDeclaration (final Compiler.Context context, final MessageDefinition message, final IndentWriter writer) throws IOException {
    super.beginClassImplementationDeclaration (context, message, writer);
    comment (writer, "TODO begin class implementation declaration");
  }
  
  @Override
  public void endClassImplementationDeclaration (final Compiler.Context context, final MessageDefinition message, final IndentWriter writer) throws IOException {
    comment (writer, "TODO end class implementation declaration");
  }
  
  @Override
  public void writeClassImplementationAttribute (final Compiler.Context context, final FieldDefinition field, final IndentWriter writer) throws IOException {
    comment (writer, "TODO class implementation attribute");
  }
  
  @Override
  public void writeClassImplementationAccessor (final Compiler.Context context, final FieldDefinition field, final IndentWriter writer) throws IOException {
    comment (writer, "TODO class implementation accessor");
  }
  
  @Override
  public void writeClassImplementationConstructor (final Compiler.Context context, final MessageDefinition message, final IndentWriter writer) throws IOException {
    comment (writer, "TODO class implementation constructor");
  }
  
  @Override
  public void writeEnumImplementationDeclaration (final Compiler.Context context, final EnumDefinition enumDefinition, final IndentWriter writer) throws IOException {
    comment (writer, "TODO enum implementation declaration");
  }

  @Override
  public void writeTaxonomyImplementationDeclaration (final Compiler.Context context, final TaxonomyDefinition taxonomyDefinition, final IndentWriter writer) throws IOException {
    comment (writer, "TODO taxonomy implementation declaration");
  }

}