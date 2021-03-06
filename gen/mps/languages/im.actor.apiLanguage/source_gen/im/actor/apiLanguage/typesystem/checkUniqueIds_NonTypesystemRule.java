package im.actor.apiLanguage.typesystem;

/*Generated by MPS */

import jetbrains.mps.lang.typesystem.runtime.AbstractNonTypesystemRule_Runtime;
import jetbrains.mps.lang.typesystem.runtime.NonTypesystemRule_Runtime;
import org.jetbrains.mps.openapi.model.SNode;
import jetbrains.mps.typesystem.inference.TypeCheckingContext;
import jetbrains.mps.lang.typesystem.runtime.IsApplicableStatus;
import jetbrains.mps.internal.collections.runtime.ListSequence;
import jetbrains.mps.lang.smodel.generator.smodelAdapter.SLinkOperations;
import jetbrains.mps.lang.smodel.generator.smodelAdapter.SNodeOperations;
import jetbrains.mps.smodel.adapter.structure.MetaAdapterFactory;
import jetbrains.mps.internal.collections.runtime.IWhereFilter;
import jetbrains.mps.lang.smodel.generator.smodelAdapter.SPropertyOperations;
import jetbrains.mps.errors.messageTargets.MessageTarget;
import jetbrains.mps.errors.messageTargets.NodeMessageTarget;
import jetbrains.mps.errors.IErrorReporter;
import jetbrains.mps.smodel.SModelUtil_new;

public class checkUniqueIds_NonTypesystemRule extends AbstractNonTypesystemRule_Runtime implements NonTypesystemRule_Runtime {
  public checkUniqueIds_NonTypesystemRule() {
  }
  public void applyRule(final SNode structAttribute, final TypeCheckingContext typeCheckingContext, IsApplicableStatus status) {
    if (ListSequence.fromList(SLinkOperations.getChildren(SNodeOperations.cast(SNodeOperations.getParent(structAttribute), MetaAdapterFactory.getInterfaceConcept(0x77fdf769432b4edeL, 0x8171050f8dee73fcL, 0x20977a66b69665d2L, "im.actor.apiLanguage.structure.IStruct")), MetaAdapterFactory.getContainmentLink(0x77fdf769432b4edeL, 0x8171050f8dee73fcL, 0x20977a66b69665d2L, 0x20977a66b6966607L, "attributes"))).where(new IWhereFilter<SNode>() {
      public boolean accept(SNode it) {
        return SPropertyOperations.getInteger(it, MetaAdapterFactory.getProperty(0x77fdf769432b4edeL, 0x8171050f8dee73fcL, 0x20977a66b68879a3L, 0x20977a66b689e4d5L, "id")) == SPropertyOperations.getInteger(structAttribute, MetaAdapterFactory.getProperty(0x77fdf769432b4edeL, 0x8171050f8dee73fcL, 0x20977a66b68879a3L, 0x20977a66b689e4d5L, "id"));
      }
    }).count() > 1) {
      {
        MessageTarget errorTarget = new NodeMessageTarget();
        IErrorReporter _reporter_2309309498 = typeCheckingContext.reportTypeError(structAttribute, "Duplicate ID", "r:6e7b088d-9a56-43ad-8e6a-4a3f15c66539(im.actor.apiLanguage.typesystem)", "2348480312265059260", null, errorTarget);
      }
    }
  }
  public String getApplicableConceptFQName() {
    return "im.actor.apiLanguage.structure.StructAttribute";
  }
  public IsApplicableStatus isApplicableAndPattern(SNode argument) {
    {
      boolean b = SModelUtil_new.isAssignableConcept(argument.getConcept().getQualifiedName(), this.getApplicableConceptFQName());
      return new IsApplicableStatus(b, null);
    }
  }
  public boolean overrides() {
    return false;
  }
}
