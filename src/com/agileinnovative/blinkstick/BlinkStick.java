/**
 * ##library.name##
 * ##library.sentence##
 * ##library.url##
 *
 * Copyright ##copyright## ##author##
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General
 * Public License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA  02111-1307  USA
 * 
 * @author      ##author##
 * @modified    ##date##
 * @version     ##library.prettyVersion## (##library.version##)
 */

package com.agileinnovative.blinkstick;


import processing.core.*;
import com.codeminders.hidapi.HIDDeviceInfo;
import com.codeminders.hidapi.HIDManager;
import com.codeminders.hidapi.HIDDevice;
import java.util.Hashtable;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

/**
 * This is a template class and can be used to start a new processing library or tool.
 * Make sure you rename this class as well as the name of the example package 'template' 
 * to your own library or tool naming convention.
 * 
 * @example Hello 
 * 
 * (the tag @example followed by the name of an example included in folder 'examples' will
 * automatically include the example in the javadoc.)
 *
 */

public class BlinkStick {
	
	public final static String VERSION = "##library.prettyVersion##";
	
	  private static Boolean Initialized = false;
	  
	  public class color {
	    private byte r;
	    private byte g;
	    private byte b;
	    
	    public color(byte r, byte g, byte b) {
	      this.r = r;
	      this.g = g;
	      this.b = b;
	    }
	    
	    public byte getR() {
	      return this.r;
	    }
	    
	    public byte getG() {
	      return this.g;
	    }
	    
	    public byte getB() {
	      return this.b;
	    }
	  }
	  
	  private static final Hashtable<String,String> COLORS = new Hashtable<String,String>() {{
	    put("aqua", "#00ffff");
	    put("aliceblue", "#f0f8ff");
	    put("antiquewhite", "#faebd7");
	    put("black", "#000000");
	    put("blue", "#0000ff");
	    put("cyan", "#00ffff");
	    put("darkblue", "#00008b");
	    put("darkcyan", "#008b8b");
	    put("darkgreen", "#006400");
	    put("darkturquoise", "#00ced1");
	    put("deepskyblue", "#00bfff");
	    put("green", "#008000");
	    put("lime", "#00ff00");
	    put("mediumblue", "#0000cd");
	    put("mediumspringgreen", "#00fa9a");
	    put("navy", "#000080");
	    put("springgreen", "#00ff7f");
	    put("teal", "#008080");
	    put("midnightblue", "#191970");
	    put("dodgerblue", "#1e90ff");
	    put("lightseagreen", "#20b2aa");
	    put("forestgreen", "#228b22");
	    put("seagreen", "#2e8b57");
	    put("darkslategray", "#2f4f4f");
	    put("darkslategrey", "#2f4f4f");
	    put("limegreen", "#32cd32");
	    put("mediumseagreen", "#3cb371");
	    put("turquoise", "#40e0d0");
	    put("royalblue", "#4169e1");
	    put("steelblue", "#4682b4");
	    put("darkslateblue", "#483d8b");
	    put("mediumturquoise", "#48d1cc");
	    put("indigo", "#4b0082");
	    put("darkolivegreen", "#556b2f");
	    put("cadetblue", "#5f9ea0");
	    put("cornflowerblue", "#6495ed");
	    put("mediumaquamarine", "#66cdaa");
	    put("dimgray", "#696969");
	    put("dimgrey", "#696969");
	    put("slateblue", "#6a5acd");
	    put("olivedrab", "#6b8e23");
	    put("slategray", "#708090");
	    put("slategrey", "#708090");
	    put("lightslategray", "#778899");
	    put("lightslategrey", "#778899");
	    put("mediumslateblue", "#7b68ee");
	    put("lawngreen", "#7cfc00");
	    put("aquamarine", "#7fffd4");
	    put("chartreuse", "#7fff00");
	    put("gray", "#808080");
	    put("grey", "#808080");
	    put("maroon", "#800000");
	    put("olive", "#808000");
	    put("purple", "#800080");
	    put("lightskyblue", "#87cefa");
	    put("skyblue", "#87ceeb");
	    put("blueviolet", "#8a2be2");
	    put("darkmagenta", "#8b008b");
	    put("darkred", "#8b0000");
	    put("saddlebrown", "#8b4513");
	    put("darkseagreen", "#8fbc8f");
	    put("lightgreen", "#90ee90");
	    put("mediumpurple", "#9370db");
	    put("darkviolet", "#9400d3");
	    put("palegreen", "#98fb98");
	    put("darkorchid", "#9932cc");
	    put("yellowgreen", "#9acd32");
	    put("sienna", "#a0522d");
	    put("brown", "#a52a2a");
	    put("darkgray", "#a9a9a9");
	    put("darkgrey", "#a9a9a9");
	    put("greenyellow", "#adff2f");
	    put("lightblue", "#add8e6");
	    put("paleturquoise", "#afeeee");
	    put("lightsteelblue", "#b0c4de");
	    put("powderblue", "#b0e0e6");
	    put("firebrick", "#b22222");
	    put("darkgoldenrod", "#b8860b");
	    put("mediumorchid", "#ba55d3");
	    put("rosybrown", "#bc8f8f");
	    put("darkkhaki", "#bdb76b");
	    put("silver", "#c0c0c0");
	    put("mediumvioletred", "#c71585");
	    put("indianred", "#cd5c5c");
	    put("peru", "#cd853f");
	    put("chocolate", "#d2691e");
	    put("tan", "#d2b48c");
	    put("lightgray", "#d3d3d3");
	    put("lightgrey", "#d3d3d3");
	    put("thistle", "#d8bfd8");
	    put("goldenrod", "#daa520");
	    put("orchid", "#da70d6");
	    put("palevioletred", "#db7093");
	    put("crimson", "#dc143c");
	    put("gainsboro", "#dcdcdc");
	    put("plum", "#dda0dd");
	    put("burlywood", "#deb887");
	    put("lightcyan", "#e0ffff");
	    put("lavender", "#e6e6fa");
	    put("darksalmon", "#e9967a");
	    put("palegoldenrod", "#eee8aa");
	    put("violet", "#ee82ee");
	    put("azure", "#f0ffff");
	    put("honeydew", "#f0fff0");
	    put("khaki", "#f0e68c");
	    put("lightcoral", "#f08080");
	    put("sandybrown", "#f4a460");
	    put("beige", "#f5f5dc");
	    put("mintcream", "#f5fffa");
	    put("wheat", "#f5deb3");
	    put("whitesmoke", "#f5f5f5");
	    put("ghostwhite", "#f8f8ff");
	    put("lightgoldenrodyellow", "#fafad2");
	    put("linen", "#faf0e6");
	    put("salmon", "#fa8072");
	    put("oldlace", "#fdf5e6");
	    put("bisque", "#ffe4c4");
	    put("blanchedalmond", "#ffebcd");
	    put("coral", "#ff7f50");
	    put("cornsilk", "#fff8dc");
	    put("darkorange", "#ff8c00");
	    put("deeppink", "#ff1493");
	    put("floralwhite", "#fffaf0");
	    put("fuchsia", "#ff00ff");
	    put("gold", "#ffd700");
	    put("hotpink", "#ff69b4");
	    put("ivory", "#fffff0");
	    put("lavenderblush", "#fff0f5");
	    put("lemonchiffon", "#fffacd");
	    put("lightpink", "#ffb6c1");
	    put("lightsalmon", "#ffa07a");
	    put("lightyellow", "#ffffe0");
	    put("magenta", "#ff00ff");
	    put("mistyrose", "#ffe4e1");
	    put("moccasin", "#ffe4b5");
	    put("navajowhite", "#ffdead");
	    put("orange", "#ffa500");
	    put("orangered", "#ff4500");
	    put("papayawhip", "#ffefd5");
	    put("peachpuff", "#ffdab9");
	    put("pink", "#ffc0cb");
	    put("red", "#ff0000");
	    put("seashell", "#fff5ee");
	    put("snow", "#fffafa");
	    put("tomato", "#ff6347");
	    put("white", "#ffffff");
	    put("yellow", "#ffff00");
	   }};
	  
	  public final static int VENDOR_ID = 0x20a0;
	  public final static int PRODUCT_ID = 0x41e5;
	  
	  private HIDDevice device = null;
	  
	/**
	 * 
	 * a Constructor, usually called in the setup() method in your sketch to
	 * initialize and start the library.
	 * 
	 * @example Hello
	 * @param theParent
	 */
	public BlinkStick() {
		
	}
	
	/**
	 * return the version of the library.
	 * 
	 * @return String
	 */
	public static String version() {
		return VERSION;
	}
	
	  private void setDevice(HIDDevice device) {
		    this.device = device;
		  }
		  
		  public static void Initialize() {
		    if (!Initialized) {
		      Initialized = true;
		      
		      com.codeminders.hidapi.ClassPathLibraryLoader.loadNativeHIDLibrary();
		    }
		  }
		  
		  public static BlinkStick findFirst() {
		    Initialize();
		    
		    HIDDeviceInfo[] infos = findAllDescriptors();
		    
		    if (infos.length > 0) {
		      BlinkStick result = new BlinkStick();
		      try
		      {
		        result.setDevice(infos[0].open());
		        return result;
		      }
		      catch (Exception e)
		      {
		      }
		    }
		    return null;  
		  }
		  
		  public static BlinkStick findBySerial(String serial) {
		    Initialize();
		    
		    HIDDeviceInfo[] infos = findAllDescriptors();
		    
		    for (HIDDeviceInfo info : infos) {
		      if (info.getSerial_number().equals(serial))
		      {
		        BlinkStick result = new BlinkStick();
		        try
		        {
		          result.setDevice(infos[0].open());
		          return result;
		        }
		        catch (Exception e)
		        {
		        }
		      }
		    }
		    
		    return null;  
		  }
		  
		  
		  private static HIDDeviceInfo[] findAllDescriptors() {
		    Initialize();
		    
		    List<HIDDeviceInfo> blinkstickList = new ArrayList<HIDDeviceInfo>();
		    
		    try
		    {
		      HIDManager hidManager = HIDManager.getInstance();
		      
		      HIDDeviceInfo[] infos = hidManager.listDevices();
		    
		      for (HIDDeviceInfo info : infos) {
		        if (info.getVendor_id() == VENDOR_ID && info.getProduct_id() == PRODUCT_ID) {
		          blinkstickList.add(info);
		        }
		      }
		    }
		    catch (Exception e)
		    {
		    }
		  
		    return blinkstickList.toArray(new HIDDeviceInfo[blinkstickList.size()]);
		  }
		  
		  public static BlinkStick[] findAll() {
		    List<BlinkStick> blinkstickList = new ArrayList<BlinkStick>();
		    
		    HIDDeviceInfo[] infos = findAllDescriptors();
		    
		    for (HIDDeviceInfo info : infos) {
		        BlinkStick blinkstick = new BlinkStick();
		        try
		        {
		          blinkstick.setDevice(info.open());
		          blinkstickList.add(blinkstick);
		        } 
		        catch (Exception e)
		        {}
		    }
		    
		    return blinkstickList.toArray(new BlinkStick[blinkstickList.size()]);
		  }
		  
		  public void setColor(int r, int g, int b) {
		    this.setColor((byte)r, (byte)g, (byte)b);
		  }
		  
		  public void setColor(byte r, byte g, byte b) {
		    byte[] data = new byte[4];
		    
		    data[0] = 1;
		    data[1] = r;
		    data[2] = g;
		    data[3] = b;
		    
		    try
		    {
		      device.sendFeatureReport(data);
		    }
		    catch (Exception e)
		    {
		      e.printStackTrace();
		     
		    }
		  }
		  
		  public void setColor(color colorValue) {
		    this.setColor(colorValue.getR(), colorValue.getG(), colorValue.getB()); 
		  }
		  
		  public void setColor(String hexValue) {
		    if (COLORS.containsKey(hexValue)) {
		      this.setColor(hex2Rgb(COLORS.get(hexValue)));
		    } else {
		      this.setColor(hex2Rgb(hexValue));
		    }
		  }
		  
		  /**
		  * 
		  * @param colorStr e.g. "#FFFFFF"
		  * @return 
		  */
		  public color hex2Rgb(String colorStr) {
		    byte r = (byte)(Integer.valueOf( colorStr.substring( 1, 3 ), 16 ) + 0);
		    byte g = (byte)(Integer.valueOf( colorStr.substring( 3, 5 ), 16 ) + 0);
		    byte b = (byte)(Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) + 0);
		    
		    return new color(r, g, b);
		  }
		  
		  public color getColor() {
		    byte[] data = new byte[33];
		    data[0] = 1;//First byte is ReportID

		    try {
		      int read = device.getFeatureReport(data);
		      if (read>0) {
		        return new color(data[1], data[2], data[3]);
		      }
		    }
		    catch (Exception e)
		    {
		    }
		    
		    return new color((byte)0, (byte)0, (byte)0);
		  }
		  
		  private String getInfoBlock(int id) {
		    byte[] data = new byte[33];
		    data[0] = (byte)(id + 1);
		    
		    String result = "";
		    try {
		      int read = device.getFeatureReport(data);
		      if (read>0) {
		        for (int i = 1; i < data.length; i++) {
		          if (i == 0) {
		            break;
		          }
		            
		          result += (char)data[i];  
		        }
		      }
		    }
		    catch (Exception e)
		    {
		    }
		    
		    return result;
		  }
		  
		  public String getInfoBlock1() {
		    return getInfoBlock(1);
		  }

		  public String getInfoBlock2() {
		    return getInfoBlock(2);
		  }
		  
		  private void setInfoBlock(int id, String value) {
		    char[] charArray = value.toCharArray();
		    byte[] data = new byte[33];
		    data[0] = (byte)(id + 1);
		    
		    for (int i = 0; i < charArray.length; i++) {
		      if (i > 32) {
		        break;
		      }
		        
		      data[i + 1] = (byte)charArray[i];  
		    }
		    
		    try
		    {
		      device.sendFeatureReport(data);
		    }
		    catch (Exception e)
		    {
		      e.printStackTrace();
		     
		    }
		  }
		  
		  public void setInfoBlock1(String value) {
		    setInfoBlock(1, value);
		  }
		  
		  public void setInfoBlock2(String value) {
		    setInfoBlock(2, value);
		  }
		  
		  public String getColorString() {
		    color c = getColor();
		    return String.format("%02X", c.getR()) + String.format("%02X", c.getG()) + String.format("%02X", c.getB());
		  }
		  
		  public void setRandomColor() {
		    Random random = new Random();
		    this.setColor(random.nextInt(256), random.nextInt(256), random.nextInt(256));
		  }
		  
		  public void turnOff() {
		    this.setColor(0, 0, 0);
		  }
		  
		  public String getManufacturer() {
		    try
		    {
		      return device.getManufacturerString();
		    }
		    catch (Exception e)
		    {
		      return "";
		    }
		  }
		  
		  public String getProduct() {
		    try
		    {
		      return device.getProductString();
		    }
		    catch (Exception e)
		    {
		      return "";
		    }
		  }
		  
		  public String getSerial() {
		    try
		    {
		      return device.getSerialNumberString();
		    }
		    catch (Exception e)
		    {
		      return "";
		    }
		  }
		  	
	
}
