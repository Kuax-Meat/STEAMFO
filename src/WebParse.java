import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.ElementIterator;
import javax.swing.text.html.*;

/**
 * @author Jae-Hyeong, Sim (Nickname MEAT)
 */
public class WebParse {
    
    /**
     * parse Steam
     * @param url
     * @param type
     * @param keyword1
     * @param keyword2
     * @param num
     * @param custom
     * @return string[]
     */
    public String[] parsesteam(String url, String type, String keyword1, int num, int custom) {
        try {
            URL receive_url = new URL(url);
            HTMLEditorKit kit = new HTMLEditorKit();
            HTMLDocument doc = (HTMLDocument)kit.createDefaultDocument();
            doc.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
            Reader HTMLReader = new InputStreamReader(receive_url.openConnection().getInputStream(), "UTF-8");
            kit.read(HTMLReader, doc, 0);
            String[] imgurl = new String[num*2];
            int imgurlindex = 0;
            int numb = num;
            ElementIterator it = new ElementIterator(doc); 
            Element elem;
            String b = null;
            
            while((elem = it.next()) != null)
            {
            	if (type.equals("class")) {
            		b = (String)elem.getAttributes().getAttribute(HTML.Attribute.CLASS);
            	}
            	else if (type.equals("id")) {
            		b = (String)elem.getAttributes().getAttribute(HTML.Attribute.ID);
            	}
            	
                if (b != null && b.equals(keyword1))
                {
                	while ((elem = it.next()) != null)
                    {
                		if (numb == 0 || imgurlindex == 10) {
                			break;
                		}
                		
                		if (elem.getAttributes().getAttribute(HTML.Tag.A) != null) {
                			String beta = elem.getAttributes().getAttribute(HTML.Tag.A).toString();
                			if (beta.contains("app/")) {
                				String tmp = beta.substring(beta.indexOf("app/") + 4, beta.indexOf("/?"));
                				if (imgurl[imgurlindex] == null) {
	                				imgurl[imgurlindex] = "http://cdn.akamai.steamstatic.com/steam/apps/" + tmp + "/header.jpg?2015-11-26";
	                				imgurlindex++;
	                				numb--;
                				}
                				
                				while ((elem = it.next()) != null)
                    			{
                    				if (elem.getAttributes().getAttribute(HTML.Attribute.CLASS).equals("discount_pct")) {
                    					int startoffset = elem.getStartOffset();
                                        int endoffset = elem.getEndOffset();
                                        int length = endoffset - startoffset;
                                        if (doc.getText(startoffset, length).length() >= 3) {
                                        	imgurl[imgurlindex + 14] = "(" + doc.getText(startoffset, length - 1);
                                        }
                    				}
                    				
                    				if (elem.getAttributes().getAttribute(HTML.Attribute.CLASS).equals("discount_final_price")) {
                    					int startoffset = elem.getStartOffset();
                                        int endoffset = elem.getEndOffset();
                                        int length = endoffset - startoffset;
                                        imgurl[imgurlindex + 14] += "%) " + doc.getText(startoffset, length);
                                        doc.remove(0, startoffset+length);
                                        break;
                    				}
                    			}
                			} else if (beta.contains("sub")) {
                				String tmp = beta.substring(beta.indexOf("sub/") + 4, beta.indexOf("/?"));
                				if (imgurl[imgurlindex] == null) {
	                				imgurl[imgurlindex] = "http://cdn.akamai.steamstatic.com/steam/subs/" + tmp + "/header.jpg?2015-11-26";
	                				imgurlindex++;
	                				numb--;
                				}
                				
                				while ((elem = it.next()) != null)
                    			{
                    				if (elem.getAttributes().getAttribute(HTML.Attribute.CLASS).equals("discount_pct")) {
                    					int startoffset = elem.getStartOffset();
                                        int endoffset = elem.getEndOffset();
                                        int length = endoffset - startoffset;
                                        if (doc.getText(startoffset, length).length() >= 3) {
                                        	imgurl[imgurlindex + 14] = "(" + doc.getText(startoffset, length - 1);
                                        }
                                        doc.remove(0, startoffset+length);
                    				}
                    				
                    				if (elem.getAttributes().getAttribute(HTML.Attribute.CLASS).equals("discount_final_price")) {
                    					int startoffset = elem.getStartOffset();
                                        int endoffset = elem.getEndOffset();
                                        int length = endoffset - startoffset;
                                        imgurl[imgurlindex + 14] += "%) " + doc.getText(startoffset, length);
                                        break;
                    				}
                    			}
                			}
                        }
                    }
                	
                	doc.remove(0, doc.getLength());
                	HTMLReader.close();
                	
                }
            }
            
            return imgurl;
        } catch(MalformedURLException e) {
            System.err.print("Invalid URL");
        } catch(IOException | BadLocationException e) {
    		
    	}
        return null;
    }
    
    public WebParse() {
        
    }
    
    public String[] parseHumble(String inputURL) {
    	try {
			String[] humbleData = new String[31];
			URL receive_url = new URL(inputURL);
	        HTMLEditorKit kit = new HTMLEditorKit(); 
	        HTMLDocument doc = (HTMLDocument)kit.createDefaultDocument(); 
	        doc.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
	        Reader HTMLReader = new InputStreamReader(receive_url.openConnection().getInputStream(), "UTF-8"); 
	        kit.read(HTMLReader, doc, 0);
	        ElementIterator it = new ElementIterator(doc); 
	        Element elem;
	        int index = 1;
	        int flag = 0;
	        
	        while ((elem = it.next()) != null) {
	        	if (flag == 0) {
		        	if (elem.getName().equals("img")) {
		        		if (elem.getAttributes().getAttribute(HTML.Attribute.CLASS).equals("promo-logo")) {
		        			humbleData[0] = elem.getAttributes().getAttribute(HTML.Attribute.SRC).toString();
		        			flag = 1;
		        		}
		        	}
	        	}
	        	
	        	if (elem.getAttributes().getAttribute(HTML.Attribute.CLASS).equals("game-video-or-image")) {
	        		if (elem.getAttributes().getAttribute(HTML.Attribute.SRC) != null) {
	        			humbleData[index] = elem.getAttributes().getAttribute(HTML.Attribute.SRC).toString();
	        			index++;
	        		}
	        	}
	        	
	        	if (elem.getAttributes().getAttribute(HTML.Attribute.CLASS).equals("prepend section-heading price bta ")) {
	        		int spos = elem.getStartOffset();
	        		int epos = elem.getEndOffset();
	        		
	        		if (humbleData[29] == null) {
	        			humbleData[29] = doc.getText(spos, epos - spos);
	        		}
	        		
	        		if (humbleData[29].length() <= doc.getText(spos, epos - spos).length())
	        		{
		        		humbleData[29] = doc.getText(spos, epos - spos);
	        		}
	        	}
	        }
	        
	        doc.remove(0, doc.getLength());
	        HTMLReader.close();
	    	return humbleData;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
    	
		return null;
    }
    
    public String[] parseGMG(String gmgURL, String type, String keyword1, String keyword2) {
    	try {
    		System.out.println(gmgURL);
			String[] gmgData = new String[100];
			URLConnection conn = new URL(gmgURL).openConnection();
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
	        HTMLEditorKit kit = new HTMLEditorKit(); 
	        HTMLDocument doc = (HTMLDocument)kit.createDefaultDocument(); 
	        doc.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
	        Reader HTMLReader = new InputStreamReader(conn.getInputStream(), "UTF-8"); 
	        kit.read(HTMLReader, doc, 0);
	        //  Get an iterator for all HTML tags.
	        ElementIterator it = new ElementIterator(doc); 
	        Element elem;
	        int index = 0;
	        int index2 = 31;
	        
	        while ((elem = it.next()) != null) {
	        	
	        	if (type.equals("class")) {
		        	if (elem.getAttributes().getAttribute(HTML.Attribute.CLASS).equals(keyword1)) {
		        		if (elem.getAttributes().getAttribute(HTML.Tag.A) != null) {
		        			String tmp = elem.getAttributes().getAttribute(HTML.Tag.A).toString();
		        			gmgData[index + 50] = "http://www.greenmangaming.com" + tmp.substring(5, tmp.indexOf(" "));
		        		}
		        		
		        		if (elem.getAttributes().getAttribute(HTML.Attribute.SRC) != null) {
		        			gmgData[index] = elem.getAttributes().getAttribute(HTML.Attribute.SRC).toString();
		        			index++;
		        		}
		        	}
	        	} else if (type.equals("id")) {
	        		if (elem.getAttributes().getAttribute(HTML.Attribute.ID).equals(keyword1)) {
		        		if (elem.getAttributes().getAttribute(HTML.Attribute.SRC) != null) {
		        			gmgData[index] = elem.getAttributes().getAttribute(HTML.Attribute.SRC).toString();
		        			index++;
		        		}
		        	}
	        	}
	        }
	        
	        doc.remove(0, doc.getLength());
	        HTMLReader.close();
	    	return gmgData;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
    	
    	return null;
    }
    
    /**
     * 사용자의 URL을 파싱하여 이미지와 링크를 추출
     * @param URL
     * @param type
     * @param keyword1
     * @param keyword2
     * @return
     */
    public String[] parseCustom(String URL, String type, String keyword1, String keyword2) {
    	try {
			String[] customData = new String[100];
			URLConnection conn = new URL(URL).openConnection();
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
	        HTMLEditorKit kit = new HTMLEditorKit(); 
	        HTMLDocument doc = (HTMLDocument)kit.createDefaultDocument(); 
	        doc.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
	        Reader HTMLReader = new InputStreamReader(conn.getInputStream(), "UTF-8"); 
	        kit.read(HTMLReader, doc, 0);
	        ElementIterator it = new ElementIterator(doc); 
	        Element elem;
	        int index = 0;
	        
	        while ((elem = it.next()) != null) {
	        	if (type.equals("class")) {
		        	if ((elem.getAttributes().getAttribute(HTML.Attribute.CLASS) != null) && (elem.getAttributes().getAttribute(HTML.Attribute.CLASS).equals(keyword1))) {
		        		
		        		if (elem.getAttributes().getAttribute(HTML.Tag.A) != null) {
		        			String tmp = elem.getAttributes().getAttribute(HTML.Tag.A).toString();
		        			customData[index + 20] = tmp.substring(5, tmp.indexOf(" "));
		        		}
		        		
		        		if (elem.getAttributes().getAttribute(HTML.Attribute.SRC) != null) {
		        			if (!elem.getAttributes().getAttribute(HTML.Attribute.SRC).equals(keyword2)) {
			        			customData[index] = elem.getAttributes().getAttribute(HTML.Attribute.SRC).toString();
			        			index++;
		        			}
		        		}
		        	}
	        	} else if (type.equals("id")) {
	        		if ((elem.getAttributes().getAttribute(HTML.Attribute.ID) != null) && (elem.getAttributes().getAttribute(HTML.Attribute.ID).equals(keyword1))) {
		        		if (elem.getAttributes().getAttribute(HTML.Attribute.SRC) != null) {
		        			if (!elem.getAttributes().getAttribute(HTML.Attribute.SRC).equals(keyword2)) {
			        			customData[index] = elem.getAttributes().getAttribute(HTML.Attribute.SRC).toString();
			        			index++;
		        			}
		        		}
		        	}
	        	}
	        	
	        	if (index >= 6) {
	        		break;
	        	}
	        }
	        
	        doc.remove(0, doc.getLength());
	        HTMLReader.close();
	    	return customData;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
    	
    	return null;
    }

}
