package br.uece.lotus.tools;

/**
 * Created by lucas on 02/03/15.
 */
import br.uece.lotus.Component;
import br.uece.lotus.Project;
import br.uece.lotus.project.ProjectDialogsHelper;
import br.uece.lotus.project.ProjectExplorer;
import br.uece.lotus.project.ProjectSerializer;
import br.uece.seed.app.UserInterface;
import br.uece.seed.ext.ExtensionManager;
import br.uece.seed.ext.Plugin;



public class TracePlugin  extends Plugin {

    private UserInterface mUserInterface;
    private ProjectExplorer mProjectExplorer;
    private ProjectDialogsHelper mProjectDialogsHelper;
    private Project p ;
    private Component c;
    private ProjectSerializer mTraceSerializer = new TraceSerializer();

    @Override
    public void onStart(ExtensionManager extensionManager) throws Exception {
        mUserInterface = (UserInterface) extensionManager.get(UserInterface.class);
        mProjectExplorer = extensionManager.get(ProjectExplorer.class);
        mProjectDialogsHelper = extensionManager.get(ProjectDialogsHelper.class);
        mUserInterface.getMainMenu().
        newItem("Model/Model from Trace")
                    .setWeight(Integer.MAX_VALUE)
                    .setAction(mImportTrace)
                    .create();

    }

        final Runnable mImportTrace = new Runnable() {
            @Override
            public void run() {
                Project p = mProjectDialogsHelper.open(mTraceSerializer, "Import Trace", "Trace Files (*.csv)", "*.csv");
                if (p != null) {
                    p.setName("Untitled");
                    mProjectExplorer.open(p);

                }
            }
        };



}
