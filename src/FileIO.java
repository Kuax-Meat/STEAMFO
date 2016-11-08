import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class FileIO {
	BufferedReader in = null;
	BufferedWriter out = null;
	
	public String[] steam = new String[5];
	public String[] humble = new String[5];
	public String[] gmg = new String[5];
	
	public String[][] custom = new String[3][5];
	

	public int customindex = 0;
	public int fieldindex = 0;
	
	/*
	 * 
	 * [SiteName]
	 * URL="";
	 * type="";
	 * key1="";
	 * key2="";
	 * 
	 */
	public FileIO() {
		getInit();
	}
	
	public void getInit() {
		try {
			BufferedReader a = new BufferedReader(new InputStreamReader(new URL("http://kuax.org/STEAMFO/init.txt").openStream()));
			String g;
			while ((g = a.readLine()) != null) {
				switch(g) {
				case "[STEAM]":
					steam[0] = a.readLine();
					steam[1] = a.readLine();
					break;
				case "[Humble]":
					humble[0] = a.readLine();
					humble[1] = a.readLine();
					break;
				case "[GMG]":
					gmg[0] = a.readLine();
					gmg[1] = a.readLine();
					gmg[2] = a.readLine();
					gmg[3] = a.readLine();
					break;
				default:
					
				}
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		openFile();
	}
	
	public void sortArray(int indexnum) {
		for (int i = 0; i < 5; i++) {
			if (indexnum < 2) {
				if (custom[indexnum + 1][i] != null) {
					custom[indexnum][i] = custom[indexnum + 1][i];
					custom[indexnum + 1][i] = null;
				} else {
					custom[indexnum][i] = null;
				}
			} else if (indexnum == 2) {
				custom[indexnum][i] = null;
			}
		}
		customindex = custom.length;
		System.out.println(customindex);
	}
	
	public void openFile() {
		try {
			customindex = 0;
			in = new BufferedReader(new InputStreamReader(new FileInputStream("STEAMFO.fo"), "UTF-8"));
			
			String tmp;
			
			try {
				while ((tmp = in.readLine()) != null) {
					if (tmp.length() >= 2) {
						if (tmp.charAt(0) == '[') {
							custom[customindex][0] = tmp.substring(1, tmp.length() - 1);
							customindex++;
							fieldindex = 0;
						} else if (tmp.substring(0, tmp.indexOf("=")).equals("URL") && tmp.contains("=")) {
							System.out.println(tmp.substring(tmp.indexOf("=") + 1, tmp.length() - 1));
							custom[customindex-1][1] = tmp.substring(tmp.indexOf("=") + 1, tmp.length() - 1);
						} else if (tmp.substring(0, tmp.indexOf("=")).equals("type") && tmp.contains("=")) {
							System.out.println(tmp.substring(tmp.indexOf("=") + 1, tmp.length() - 1));
							custom[customindex-1][2] = tmp.substring(tmp.indexOf("=") + 1, tmp.length() - 1);
						} else if (tmp.substring(0, tmp.indexOf("=")).equals("key1") && tmp.contains("=")) {
							System.out.println(tmp.substring(tmp.indexOf("=") + 1, tmp.length() - 1));
							custom[customindex-1][3] = tmp.substring(tmp.indexOf("=") + 1, tmp.length() - 1);
						} else if (tmp.substring(0, tmp.indexOf("=")).equals("key2") && tmp.contains("=")) {
							System.out.println(tmp.substring(tmp.indexOf("=") + 1, tmp.length() - 1));
							custom[customindex-1][4] = tmp.substring(tmp.indexOf("=") + 1, tmp.length() - 1);
						} else {
							System.out.println(tmp.substring(0, tmp.indexOf("=")));
						}
					}
				}
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			createFile();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveFile() {
		OutputStreamWriter file;
		try {
			file = new OutputStreamWriter(new FileOutputStream("STEAMFO.fo"), "UTF-8");
			out = new BufferedWriter(file);
			System.out.println("asdfasdfa");
			for (int i = 0; i < 3; i++) {
				if (custom[i][0] != null) {
					out.append("[" + custom[i][0] + "]");
					out.newLine();
				}
				if (custom[i][1] != null) {
					out.append("URL=" + custom[i][1] + ";");
					out.newLine();
				}
				if (custom[i][2] != null) {
					out.append("type=" + custom[i][2] + ";");
					out.newLine();
				}
				if (custom[i][3] != null) {
					out.append("key1=" + custom[i][3] + ";");
					out.newLine();
				}
				if (custom[i][4] != null) {
					out.append("key2=" + custom[i][4] + ";");
					out.newLine();
				}
				out.append("\n");
			}
			out.close();
			file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	public void createFile() {
		OutputStreamWriter file;
		try {
			file = new OutputStreamWriter(new FileOutputStream("STEAMFO.fo"), "UTF-8");
			out = new BufferedWriter(file);
			openFile();
			out.close();
			file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
