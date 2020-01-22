package remasp.controller;

import remasp.view.RemaspView;

public class RemaspController {
	public RemaspView remaspView = new RemaspView();
	private RemaspEditorController editorController = new RemaspEditorController(this.remaspView);
	private RemaspMachineController machineController = new RemaspMachineController(this.remaspView);
}
