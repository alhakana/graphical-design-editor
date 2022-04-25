package gui.swing.controller;

import gui.swing.controller.command.CommandRedo;
import gui.swing.controller.command.CommandUndo;
import gui.swing.controller.graphical.drawings.DrawCircle;
import gui.swing.controller.graphical.drawings.DrawRectangle;
import gui.swing.controller.graphical.drawings.DrawTriangle;
import gui.swing.controller.graphical.operations.*;
import gui.swing.controller.node.*;
import gui.swing.controller.repository.*;

public class ActionManager {
    private NewProjectAction newProjectAction;
    private NewDocumentAction newDocumentAction;
    private NewPageAction newPageAction;
    
    private DeleteNodeAction deleteAction;
    
    private ShareAction shareAction;

	private RenameAction renameAction;
    
    private AboutAction aboutAction;
    
    // Graphical toolbar
    private DrawRectangle drawRectangle;
    private DrawCircle drawCircle;
    private DrawTriangle drawTriangle;
    private SlotSelect slotSelect;
    private SlotMove slotMove;
    private SlotResize slotResize;
    private SlotRotate slotRotate;
    private SlotDelete slotDelete;
    
    // Command
    private CommandUndo commandUndo;
    private CommandRedo commandRedo;
    
    // Open and save
    private SwitchWorkspace openWorkspace;
    private OpenProject openProject;
    private SaveWorkspace saveWorkspace;
    private SaveProject saveProject;
    private SaveAsProject saveAsProject;
    
    // Graphics for slot
    private GraphSlotAction graphSlotAction;
    private ChangeImgAction changeImgAction;
    private TextSlotAction textSlotAction;
    
    public ActionManager() {
        initialiseActions();
    }

    private void initialiseActions() {
        newProjectAction = new NewProjectAction();
        newDocumentAction = new NewDocumentAction();
        newPageAction = new NewPageAction();
        
        renameAction = new RenameAction();
        
        aboutAction = new AboutAction();
    
        deleteAction = new DeleteNodeAction();
        
        shareAction = new ShareAction();
        
        drawRectangle = new DrawRectangle();
        drawCircle = new DrawCircle();
        drawTriangle = new DrawTriangle();
        slotSelect = new SlotSelect();
        slotMove = new SlotMove();
        slotResize = new SlotResize();
        slotRotate = new SlotRotate();
        slotDelete = new SlotDelete();
        
        commandUndo = new CommandUndo();
        commandRedo = new CommandRedo();
        
        openWorkspace = new SwitchWorkspace();
        openProject = new OpenProject();
        saveWorkspace = new SaveWorkspace();
        saveProject = new SaveProject();
        saveAsProject = new SaveAsProject();
        
        
        graphSlotAction = new GraphSlotAction();
        changeImgAction = new ChangeImgAction();
        textSlotAction = new TextSlotAction();
    }

	public TextSlotAction getTextSlotAction() {
		return textSlotAction;
	}

	public ChangeImgAction getChangeImgAction() {
		return changeImgAction;
	}

	public GraphSlotAction getGraphSlotAction() {
		return graphSlotAction;
	}

	public SaveAsProject getSaveAsProject() {
		return saveAsProject;
	}

	public Action getDrawRectangle() {
		return drawRectangle;
	}

	public Action getDrawCircle() {
		return drawCircle;
	}

	public Action getDrawTriangle() {
		return drawTriangle;
	}

	public Action getDeleteAction() {
		return deleteAction;
	}

	public Action getNewProjectAction() {
    	return newProjectAction;
    }
    
    public Action getShareAction() {
		return shareAction;
	}

	public Action getNewDocumentAction() {
    	return newDocumentAction;
    }
    
    public Action getNewPageAction() {
    	return newPageAction;
    }
    
	public Action getRenameAction() {
		return renameAction;
	}
	
	public Action getAboutAction() {
		return aboutAction;
	}
	
	public Action getSlotSelect() {
		return slotSelect;
	}
	
	public Action getSlotMove() {
		return slotMove;
	}
	
	public Action getSlotResize() {
		return slotResize;
	}
	
	public Action getSlotRotate() {
		return slotRotate;
	}
	
	public Action getSlotDelete() {
		return slotDelete;
	}

	public Action getCommandUndo() {
		return commandUndo;
	}
	
	public Action getCommandRedo() {
		return commandRedo;
	}
    
    public Action getOpenWorkspace() {
		return openWorkspace;
	}

	public Action getOpenProject() {
		return openProject;
	}

	public Action getSaveWorkspace() {
		return saveWorkspace;
	}

	public Action getSaveProject() {
		return saveProject;
	}
	
}
