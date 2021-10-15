package RegisterDetailViewProps;

import JDBCController.TableRegister;
import SpecificViews.*;


public class GrupoRegisterProps extends RegisterDetail{

    public GrupoRegisterProps(OperationInfoPanel infoPanel) {
        super(infoPanel);
        overRideDefOp("modificar", new GrupoModificatorOp(infoPanel));
        addPills();
        addOps();

    }

    private void addPills(){
        addPill( new AsignturasPillChoser());
        addPill( new PlanoGrupoViewerPill());
    }

    private void addOps(){
        addOperation(new HorarioBuilder(infoPanel));
        addOperation(new HorarioConsulter(infoPanel));
        addOperation(new GrupoCalifConsulter(infoPanel));
        addOperation(new ListadoAlumnosOp(infoPanel));
        addOperation(new ConcentradoTodasMateriasOp(infoPanel));
        addOperation(new BitacoraMateriaGrupo(infoPanel));
        addOperation(new PlanesEstudioChoserOp(infoPanel));

    }

}
