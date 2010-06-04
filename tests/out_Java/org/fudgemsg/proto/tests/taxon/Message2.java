// Automatically created - do not modify
/* fileheader.java.include
 *
 * This file is part of the Java unit test generated output.
 */
// Created from taxonomy.proto:29(9)
package org.fudgemsg.proto.tests.taxon;
public class Message2 implements java.io.Serializable {
  private static final long serialVersionUID = -643813289697l;
  private final Integer _bar;
  public static final int BAR_ORDINAL = 10;
  private final Integer _cow;
  public static final String COW_KEY = "cow";
  public static class Builder {
    private Integer _bar;
    private Integer _cow;
    public Builder () {
    }
    protected Builder (final org.fudgemsg.FudgeFieldContainer fudgeMsg) {
      org.fudgemsg.FudgeField fudgeField;
      fudgeField = fudgeMsg.getByOrdinal (BAR_ORDINAL);
      if (fudgeField != null)  {
        try {
          bar (fudgeMsg.getFieldValue (Integer.class, fudgeField));
        }
        catch (IllegalArgumentException e) {
          throw new IllegalArgumentException ("Fudge message is not a Message2 - field 'bar' is not integer", e);
        }
      }
      fudgeField = fudgeMsg.getByName (COW_KEY);
      if (fudgeField != null)  {
        try {
          cow (fudgeMsg.getFieldValue (Integer.class, fudgeField));
        }
        catch (IllegalArgumentException e) {
          throw new IllegalArgumentException ("Fudge message is not a Message2 - field 'cow' is not integer", e);
        }
      }
    }
    public Builder bar (Integer bar) {
      _bar = bar;
      return this;
    }
    public Builder cow (Integer cow) {
      _cow = cow;
      return this;
    }
    public Message2 build () {
      return new Message2 (this);
    }
  }
  protected Message2 (final Builder builder) {
    _bar = builder._bar;
    _cow = builder._cow;
  }
  public Message2 (Integer bar, Integer cow) {
    _bar = bar;
    _cow = cow;
  }
  protected Message2 (final Message2 source) {
    if (source == null) throw new NullPointerException ("'source' must not be null");
    _bar = source._bar;
    _cow = source._cow;
  }
  public org.fudgemsg.FudgeFieldContainer toFudgeMsg (final org.fudgemsg.FudgeMessageFactory fudgeContext) {
    if (fudgeContext == null) throw new NullPointerException ("fudgeContext must not be null");
    final org.fudgemsg.MutableFudgeFieldContainer msg = fudgeContext.newMessage ();
    toFudgeMsg (fudgeContext, msg);
    return msg;
  }
  public void toFudgeMsg (final org.fudgemsg.FudgeMessageFactory fudgeContext, final org.fudgemsg.MutableFudgeFieldContainer msg) {
    if (_bar != null)  {
      msg.add (null, BAR_ORDINAL, _bar);
    }
    if (_cow != null)  {
      msg.add (COW_KEY, null, _cow);
    }
  }
  public static Message2 fromFudgeMsg (final org.fudgemsg.FudgeFieldContainer fudgeMsg) {
    final java.util.List<org.fudgemsg.FudgeField> types = fudgeMsg.getAllByOrdinal (0);
    for (org.fudgemsg.FudgeField field : types) {
      final String className = (String)field.getValue ();
      if ("org.fudgemsg.proto.tests.taxon.Message2".equals (className)) break;
      try {
        return (org.fudgemsg.proto.tests.taxon.Message2)Class.forName (className).getDeclaredMethod ("fromFudgeMsg", org.fudgemsg.FudgeFieldContainer.class).invoke (null, fudgeMsg);
      }
      catch (Throwable t) {
        // no-action
      }
    }
    return new Builder (fudgeMsg).build ();
  }
  public Integer getBar () {
    return _bar;
  }
  public Integer getCow () {
    return _cow;
  }
  public boolean equals (final Object o) {
    if (o == this) return true;
    if (o == null) return false;
    if (!(o instanceof Message2)) return false;
    Message2 msg = (Message2)o;
    if (_bar != null) {
      if (msg._bar != null) {
        if (!_bar.equals (msg._bar)) return false;
      }
      else return false;
    }
    else if (msg._bar != null) return false;
    if (_cow != null) {
      if (msg._cow != null) {
        if (!_cow.equals (msg._cow)) return false;
      }
      else return false;
    }
    else if (msg._cow != null) return false;
    return true;
  }
  public int hashCode () {
    int hc = 1;
    hc *= 31;
    if (_bar != null) hc += _bar.hashCode ();
    hc *= 31;
    if (_cow != null) hc += _cow.hashCode ();
    return hc;
  }
  public String toString () {
    return org.apache.commons.lang.builder.ToStringBuilder.reflectionToString(this, org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE);
  }
}
/* filefooter.java.include
 *
 * This file is part of the Java unit test generated output.
 */
