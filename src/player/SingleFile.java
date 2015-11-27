package player;

import java.io.File;

public class SingleFile 
{
	private File file;
	
	public SingleFile(File file){
		this.file = file;
	}
	
	public File getF(){
		return file;
	}
	
	public String toString(){
		return file.getName();
	}
}
