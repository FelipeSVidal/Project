/*
 * The MIT License
 *
 * Copyright 2014 emerson.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.uece.lotus.project;

import br.uece.lotus.Project;
import br.uece.seed.app.DialogsHelper;
import br.uece.seed.ext.ExtensionManager;
import br.uece.seed.ext.Plugin;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author emerson
 */
public class ProjectDialogsHelper extends Plugin {

    private FileChooser mFileChooser;
    private DialogsHelper mDialogsHelper;

    @Override
    public void onStart(ExtensionManager extensionManager) throws Exception {
        mDialogsHelper = extensionManager.get(DialogsHelper.class);
    }

    public void save(Project project, ProjectSerializer serializer, String title, String extensionDescription, String extension) {
        realSave(project, serializer, false, title, extensionDescription, extension);
    }

    public void saveAs(Project project, ProjectSerializer serializer, String title, String extensionDescription, String extension) {
        realSave(project, serializer, true, title, extensionDescription, extension);
    }

    public Project open(ProjectSerializer serializer, String title, String extensionDescription, String extension) {
        File file = getFileChooser(title, extensionDescription, extension, null).showOpenDialog(null);
        if (file == null) {
            return null;
        }
        Project p = null;
        try (FileInputStream in = new FileInputStream(file)) {
            p = serializer.parseStream(in);
            p.setValue("file", file);
        } catch (Exception e) {
            mDialogsHelper.showException(e);
        }
        return p;
    }

    private void realSave(Project project, ProjectSerializer serializer, boolean force, String title, String extensionDescription, String extension) {
        if (project == null) {
            JOptionPane.showMessageDialog(null, "Please select a project!");
            return;
        }
        File f = (File) project.getValue("file");
        if (force || f == null) {
            f = getFileChooser(title, extensionDescription, extension, project.getName()).showSaveDialog(null);
            if (f == null) {
                return;
            }
        }
        try (FileOutputStream out = new FileOutputStream(f)) {
            serializer.toStream(project, out);
            project.setValue("file", f);            
        } catch (Exception e) {
            mDialogsHelper.showException(e);
        }
    }

    private FileChooser getFileChooser(String title, String extensionDescription, String extension, String defaultFileName) {
        if (mFileChooser == null) {
            mFileChooser = new FileChooser();
            mFileChooser.setInitialDirectory(
                    new File(System.getProperty("user.home"))
            );
            mFileChooser.setInitialFileName(defaultFileName);
            mFileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter(extensionDescription, extension),
                    new FileChooser.ExtensionFilter("All files", "*")
            );
        }
        mFileChooser.setTitle(title);
        return mFileChooser;
    }

    private String removeExtension(String name) {        
        int i = name.lastIndexOf('.');
        if (i > 0) {
            return name.substring(0, i - 1);
        }
        return name;
    }
}
