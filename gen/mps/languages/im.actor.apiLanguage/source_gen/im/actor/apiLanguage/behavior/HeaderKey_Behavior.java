package im.actor.apiLanguage.behavior;

/*Generated by MPS */

import org.jetbrains.mps.openapi.model.SNode;
import jetbrains.mps.lang.smodel.generator.smodelAdapter.SPropertyOperations;

public class HeaderKey_Behavior {
  public static void init(SNode thisNode) {
    SPropertyOperations.set(thisNode, "hexValue", "01");
  }

  public static int call_intValue_4689615199750893375(SNode thisNode) {
    try {
      return Integer.parseInt(SPropertyOperations.getString(thisNode, "hexValue"), 16);
    } catch (Exception e) {
      return 0;
    }
  }
}