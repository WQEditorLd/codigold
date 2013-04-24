package org.sakaiproject.authoring.jgraphx;

import com.mxgraph.io.mxCellCodec;
import com.mxgraph.io.mxCodecRegistry;

public class AuthoringCellCodec extends mxCellCodec {
	
	static {
		mxCodecRegistry.addPackage("org.sakaiproject.authoring.jgraphx");
		
		mxCodecRegistry.register(new AuthoringCellCodec());
		
	}

	public AuthoringCellCodec(){
		super(new AuthoringCell());
	}
	
}
