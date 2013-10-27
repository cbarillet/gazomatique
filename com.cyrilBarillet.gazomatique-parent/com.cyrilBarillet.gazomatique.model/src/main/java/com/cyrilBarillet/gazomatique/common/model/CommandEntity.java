package com.cyrilBarillet.gazomatique.common.model;

public class CommandEntity {
	
	private InstructionEnum instruction;
	
	public CommandEntity(InstructionEnum instruction) {
		super();
		this.instruction = instruction;
	}
	
	public InstructionEnum getInstruction() {
		return instruction;
	}
	
	@Override
	public String toString(){
		return this.getInstruction().toString();
	}
}
