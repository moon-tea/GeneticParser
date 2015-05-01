import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
/*
 * @author: Monte Nichols II
 */
public class GeneticParser {
	//--Start Main
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		File path = new File("in");		
		
		File [] files = path.listFiles();
		
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()){ //this line weeds out other directories/folders
				System.out.println(files[i]);
				PrintWriter writer = new PrintWriter("out/" + files[i].getName(), "UTF-8");
				Scanner in = new Scanner(files[i]);	

				int count = 0;
				int currentInt = 1;
				int headerCount = 0;

				int headerAmount = 21;
				int sampleAmount = 20;			  
				if(args.length > 0 ){
					headerAmount = Integer.parseInt((args[0]));
					sampleAmount = Integer.parseInt((args[1]));			  
				}
			   
				while(in.hasNextLine()) // read each line
				{ 
					String line = in.nextLine();					
					if(headerCount < headerAmount) {				
						writer.println(line);
						headerCount++;
					} else {
						if(currentInt == Character.getNumericValue(line.charAt(0))) {
							if(count < sampleAmount) {
								writer.println(line);
							}
							if (count == sampleAmount) {
								currentInt++;
								writer.println();
							}
							count++;
						} else {
							count = 0;
						}
					}
				}//		end while
				writer.close();
			}//			end if(file)
		}//				end file iterator
	}//					end main	
}//						end