package com.fs.dev;

public class TheUtility {

	/**
	 * DMYPICTURE is dd/MM/yyyy ex. 01/12/2005
	 */
	public static final String DMYPICTURE = "dd/MM/yyyy";
	/**
	 * YMDPICTURE is yyyyMMdd ex. 2005-12-01
	 */
	public static final String YMDPICTURE = "yyyyMMdd";
	/**
	 * HMSPICTURE is HH:mm:ss display in 24 hours. ex. 23:35:20
	 */
	public static final String HMSPICTURE = "HH:mm:ss";
	/**
	 * DMY_HMSPICTURE is dd/MM/yyyy HH:mm:ss
	 */
	public static final String DMY_HMSPICTURE = "dd/MM/yyyy HH:mm:ss";
	/**
	 * YMD_HMSPICTURE is yyyy-MM-dd HH:mm:ss
	 */
	public static final String YMD_HMSPICTURE = "yyyy-MM-dd HH:mm:ss";
	
	public TheUtility() {
		super();
	}

	/**
	 * To compare two calendar with date in format yyyyMMdd
	 * the returning value is the following <br>
	 * -1 -> aCalendar is less than bCalendar <br>
	 *  0 -> aCalendar is equal to bCalendar <br>
	 *  1 -> aCalendar is greater than bCalendar <br>
	 * and if aCalendar or bCalendar is null it's return -99
	 */
	public static int compareDate(java.util.Calendar aCalendar,java.util.Calendar bCalendar) {
		if((aCalendar==null) && (bCalendar==null)) {
			return 0;
		}
		if((aCalendar==null) && (bCalendar!=null)) {
			return -1;
		}
		if((aCalendar!=null) && (bCalendar==null)) {
			return 1;
		}
		String adate = formatDateTime(aCalendar.getTime(),YMDPICTURE);
		String bdate = formatDateTime(bCalendar.getTime(),YMDPICTURE);	
		return adate.compareTo(bdate);
	}
	
	/**
	 * To compare two date with date in format yyyyMMdd
	 * the returning value is the following <br>
	 * -1 -> aDate is less than bDate <br>
	 *  0 -> aDate is equal to bDate <br>
	 *  1 -> aDate is greater than bDate <br>
	 * and if aDate or bDate is null it's return -99
	 */
	public static int compareDate(java.util.Date aDate,java.util.Date bDate) {
		if((aDate==null) && (bDate==null)) {
			return 0;
		}
		if((aDate==null) && (bDate!=null)) {
			return -1;
		}
		if((aDate!=null) && (bDate==null)) {
			return 1;
		}
		String adate = formatDateTime(aDate,YMDPICTURE);
		String bdate = formatDateTime(bDate,YMDPICTURE);	
		return adate.compareTo(bdate);
	}
	
	/**
	 * To compare two time with time in format hh:mm:ss
	 * the returning value is the following <br>
	 * -1 -> aTime is less than bTime <br>
	 *  0 -> aTime is equal to bTime <br>
	 *  1 -> aTime is greater than bTime <br>
	 * and if aTime or bTime is null it's return -99
	 */
	public static int compareTime(java.sql.Time aTime,java.sql.Time bTime) {
		if((aTime==null) && (bTime==null)) {
			return 0;
		}
		if((aTime==null) && (bTime!=null)) {
			return -1;
		}
		if((aTime!=null) && (bTime==null)) {
			return 1;
		}
		String atime = formatDateTime(aTime,HMSPICTURE);
		String btime = formatDateTime(bTime,HMSPICTURE);	
		return atime.compareTo(btime);
	}
	
	/**
	 * To compare two time with timestamp in format yyyy-MM-dd HH:mm:ss
	 * the returning value is the following <br>
	 * -1 -> aTimestamp is less than bTimestamp <br>
	 *  0 -> aTimestamp is equal to bTimestamp <br>
	 *  1 -> aTimestamp is greater than bTimestamp <br>
	 * and if aTimestamp or bTimestamp is null it's return -99
	 */
	public static int compareTimestamp(java.sql.Timestamp aTimestamp,java.sql.Timestamp bTimestamp) {
		if((aTimestamp==null) && (bTimestamp==null)) {
			return 0;
		}
		if((aTimestamp==null) && (bTimestamp!=null)) {
			return -1;
		}
		if((aTimestamp!=null) && (bTimestamp==null)) {
			return 1;
		}
		String atime = formatDateTime(aTimestamp,YMD_HMSPICTURE);
		String btime = formatDateTime(bTimestamp,YMD_HMSPICTURE);	
		return atime.compareTo(btime);
	}
	
	public static int compareTimestamp(java.util.Calendar aCalendar,java.util.Calendar bCalendar) {
		if((aCalendar==null) && (bCalendar==null)) {
			return 0;
		}
		if((aCalendar==null) && (bCalendar!=null)) {
			return -1;
		}
		if((aCalendar!=null) && (bCalendar==null)) {
			return 1;
		}
		java.sql.Timestamp aTimestamp = new java.sql.Timestamp(aCalendar.getTimeInMillis());
		java.sql.Timestamp bTimestamp = new java.sql.Timestamp(bCalendar.getTimeInMillis());
		String atime = formatDateTime(aTimestamp,YMD_HMSPICTURE);
		String btime = formatDateTime(bTimestamp,YMD_HMSPICTURE);	
		return atime.compareTo(btime);		
	}
	public static String formatDateTime(java.util.Date date, String picture) {
		if(date!=null) {
			java.text.SimpleDateFormat dformat = getDateFormat(picture);
			java.util.Calendar calendar = getCalendar();
			dformat.setTimeZone(calendar.getTimeZone());
			dformat.setCalendar(calendar);
			return dformat.format(date);
		}
		return null;
	}
	
	public static java.util.Calendar getCalendar() {
		return java.util.Calendar.getInstance(java.util.Locale.US);
	}
	
	public static java.util.Calendar getCalendar(java.util.Date date) {
		if(date==null) {
			return null;
		}
		java.util.Calendar calendar = getCalendar();
		calendar.setTime(date);
		return calendar;
	}

	public static java.util.Calendar getCalendar(String date) {
		if(date==null) {
			return null;
		}
		if(date.trim().length()<=0) {
			return null;		
		}
		int idx = date.indexOf('T');
		if(idx>0) {
			date = date.replaceAll("\\s+","");
			String daystr = date.substring(0,idx);
			String timestr = date.substring(idx+1);
			idx = timestr.indexOf('+');
			if(idx>0) {
				timestr = timestr.substring(0, idx);
			} else {
				idx = timestr.indexOf('.');
				if(idx>0) {
					timestr = timestr.substring(0,idx);
				} else {
					idx = timestr.indexOf('Z');
					if(idx>0) {
						timestr = timestr.substring(0,idx);
					}
				}
			}
			return parseCalendar(daystr+" "+timestr);
		}		
		return parseCalendar(date);
	}
	
	public static java.sql.Date getCurrentDate() {
		java.sql.Timestamp stamp = getCurrentTimestamp();
		return new java.sql.Date(stamp.getTime());
	}
	
	public static java.sql.Time getCurrentTime() {
		java.sql.Timestamp stamp = getCurrentTimestamp();
		return new java.sql.Time(stamp.getTime());
	}
	
	public static java.sql.Timestamp getCurrentTimestamp() {
		try {
			java.time.Instant inst = java.time.Instant.now();
			java.time.ZoneId zone = getCurrentZoneId();
			java.time.ZonedDateTime now = java.time.ZonedDateTime.ofInstant(inst, zone);
			return java.sql.Timestamp.valueOf(now.toLocalDateTime());
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return new java.sql.Timestamp(System.currentTimeMillis());
	}
	
	public static java.util.TimeZone getCurrentTimeZone() {
		return java.util.TimeZone.getDefault();
	}
	
	public static java.time.ZoneId getCurrentZoneId() {
		return java.time.ZoneId.systemDefault();
	}
	
	public static java.text.SimpleDateFormat getDateFormat(String picture) {
		return new java.text.SimpleDateFormat(picture,java.util.Locale.US);
	}
	
	/**
	 * Gets date from String DMY (dd/mm/yyyy)
	 * @return int
	 */
	public static int getDateFromDMY(String dmy) {
		int result = 0;
		String delimiter = "";
		if(dmy.trim().indexOf('/')>0 ) {
			delimiter = "/";
		} else if(dmy.trim().indexOf('-')>0) {
			delimiter = "-";
		} else if(dmy.trim().indexOf('.')>0) {
			delimiter = ".";
		}
		if(delimiter.trim().length()>0) {
			try {
				String dd = "0";
				java.util.StringTokenizer tok = new java.util.StringTokenizer(dmy,delimiter);
				if(tok.hasMoreTokens()) {
					dd = tok.nextToken();
				}
				return (Integer.parseInt(dd));
			}catch(Exception e) {
				return result;
			}
		}
		return result;
	}

	/**
	 * Gets date from String YMD (yyyymmdd)
	 * @return int
	 */
	public static int getDateFromYMD(String ymd) {
		if(ymd.length()>=8) {
			try {
				String d = ymd.substring(6,8);
				return(Integer.parseInt(d));
			}catch(Exception e) {
				return 0;
			}
		}
		return 0;
	}
	
	public static int getDayInMonth(int month,int year) {
		switch(month+1) {
			case 4 :
			case 6 :
			case 9 :
			case 11: 
					return 30;
			case 1 :
			case 3 :
			case 5 :
			case 7 :
			case 8 :
			case 10:
			case 12: 
					return 31;
			case 2 :
					if((year % 400) == 0) {
						return 29;
					} else if((year % 100) == 0) {
						return 28;					
					} else if((year % 4) == 0) {
						return 29;
					} else {
						return 28;
					}
		}	
		return 0;
	}

	public static int getDayInMonth(java.util.Calendar calendar) {
		if(calendar==null) {
			return 0;
		}
		int month = calendar.get(java.util.Calendar.MONTH);
		int year = calendar.get(java.util.Calendar.YEAR);
		return getDayInMonth(month,year);
	}
	
	public static int getDayInMonth(java.util.Date date) {
		if(date==null) {
			return 0;
		}
		return getDayInMonth(getCalendar(date));
	}
	
	public static String getDigits(String source) {
		return getDigits(source,true);
	}
	
	public static String getDigits(String source,boolean sign) {
		if(source!=null) {
			StringBuilder buf = new StringBuilder();
			for(int i=0,isz=source.length();i<isz;i++) {
				char c = source.charAt(i);
				if(c=='0' || c=='1' || c=='2' || c=='3' || c=='4' || c=='5' || c=='6' || c=='7' || c=='8' || c=='9' || c=='.') {
					buf.append(c);
				} else if((c=='-' || c=='+') && sign) {
					buf.append(c);
				}
			}
			return buf.toString();
		}
		return source;
	}
	
	/**
	 * Gets month from String DMY (dd/mm/yyyy)
	 * @return int
	 */
	public static int getMonthFromDMY(String dmy) {
		int result = 0;
		String delimiter = "";
		if(dmy.trim().indexOf('/')>0 ) {
			delimiter = "/";
		} else if(dmy.trim().indexOf('-')>0) {
			delimiter = "-";
		} else if(dmy.trim().indexOf('.')>0) {
			delimiter = ".";
		}
		if(delimiter.trim().length()>0) {
			try {
				String mm = "0";
				java.util.StringTokenizer tok = new java.util.StringTokenizer(dmy,delimiter);
				if(tok.hasMoreTokens()) {
					tok.nextToken();
				}
				if(tok.hasMoreTokens()) {
					mm = tok.nextToken();
				}
				return (Integer.parseInt(mm));
			}catch(Exception e) {
				return result;
			}
		}
		return result;
	}
	
	/**
	 * Gets month from String YMD (yyyymmdd)
	 * @return int
	 */
	public static int getMonthFromYMD(String ymd) {
		if(ymd.length()>=8) {
			try{
				String m = ymd.substring(4,6);
				return(Integer.parseInt(m));
			}catch(Exception e) {
				return 0;
			}
		}
		return 0;
	}
	
	/**
	 * Gets year from String DMY (dd/mm/yyyy)
	 * @return int 
	 */
	public static int getYearFromDMY(String dmy) {
		int result = 0;
		String delimiter = "";
		if(dmy.trim().indexOf('/')>0 ) {
			delimiter = "/";
		} else if(dmy.trim().indexOf('-')>0) {
			delimiter = "-";
		} else if(dmy.trim().indexOf('.')>0) {
			delimiter = ".";
		}
		if(delimiter.trim().length()>0) {
			try {
				String yy = "0";
				java.util.StringTokenizer tok = new java.util.StringTokenizer(dmy,delimiter);
				if(tok.hasMoreTokens()) {
					tok.nextToken();
				}
				if(tok.hasMoreTokens()) {
					tok.nextToken();
				}
				if(tok.hasMoreTokens()) {
					yy = tok.nextToken();
				}
				return (Integer.parseInt(yy));
			}catch(Exception e) {
				return result;
			}
		}
		return result;
	}
	
	/**
	 * Gets year from String YMD (yyyymmdd)
	 * @return int
	 */
	public static int getYearFromYMD(String ymd) {
		if(ymd.length()>=8) {
			try {
				String y = ymd.substring(0,4);
				return(Integer.parseInt(y));
			}catch(Exception e) {
				return 0;
			}
		}
		return 0;
	}
	
	public static boolean isDate(String str) {
		String delimiter = "";
		if(str.trim().indexOf('/')>0 ) {
			delimiter = "/";
		} else if(str.trim().indexOf('-')>0) {
			delimiter = "-";
		} else if(str.trim().indexOf('.')>0) {
			delimiter = ".";
		}
		if(delimiter.trim().length()>0) {
			String dd = "";
			String mm = "";
			String yy = "";
			java.util.StringTokenizer tok = new java.util.StringTokenizer(str,delimiter);
			if(tok.hasMoreTokens()) {
				dd = tok.nextToken();
			}
			if(tok.hasMoreTokens()) {
				mm = tok.nextToken();
			}
			if(tok.hasMoreTokens()) {
				yy = tok.nextToken();
			}
			try {			
				int day = Integer.parseInt(dd);
				int month = Integer.parseInt(mm);
				int year = Integer.parseInt(yy);		
				if((month<=0) || (month>12)) {
					return false;
				}
				if(year<=0) {
					return false;
				}
				int date = 0;
				switch(month) {
	  				case 4 :
	  				case 6 :
	  				case 9 :
	  				case 11: 
	  						date = 30;
	  						break;
		    		case 1 :
		    		case 3 :
		    		case 5 :
		    		case 7 :
		    		case 8 :
		    		case 10:
		    		case 12: 
		    				date = 31;
		    				break;
	  	  			case 2 :
	  	  					if((year % 400) == 0) {
					  			date = 29;
	  	  					} else if((year % 100) == 0) {
								date = 28;					
	  	  					} else if((year % 4) == 0) {
		    	     			date = 29;
	  	  					} else {
	  	  						date = 28;
	  	  					}
		        			break;
		  		}
				if((day<=0) || (day>date)) {
					return false;
				}
			}catch(Exception e) {
				return false;	
			}
			return true;
		}
		return false;
	}
		
	public static java.math.BigDecimal parseBigDecimal(String value) {
		java.math.BigDecimal result = null;
	   	String text = removeCommaText(value);
	   	if(text!=null) {
			text = getDigits(text);
			if(text.trim().length()>0) {
	         	try {
	            	java.math.BigDecimal signature = new java.math.BigDecimal(1);
	            	int pos = text.indexOf('+');
	            	if(pos>=0) {
	             	  text =  text.substring(pos+1);
	            	}
	            	if(pos<0) {
	             		pos = text.indexOf('-');
	               		if(pos>=0) {
	                  		text = text.substring(pos+1);
	                  		signature = new java.math.BigDecimal(-1);
	               		}
	            	}
	            	result = new java.math.BigDecimal(text);
	            	result = result.multiply(signature);
	         	} catch(Exception ex){
	         	}
	      	}
	   	}
	   	return result;
	}
	
	/**
	 * Gets calendar from date string
	 * 
	 * @return java.util.Calendar
	 * @param date java.lang.String  must in format yyyyMMdd or dd/MM/yyyy or yyyy/MM/dd
	 */
	public static java.util.Calendar parseCalendar(String date) {
		if(date==null) {
			return null;
		}
		if(date.trim().length()<=0) {
			return null;
		}
		java.util.Calendar calendar = getCalendar();
		int day = 0;
		int month = 0;
		int year = 0;
		int hour = 0;
		int minute = 0;
		int second = 0;
		int mili = 0;
		String delimiter = "";
		//date in format dd/MM/yyyy, yyyy/MM/dd
		if(date.trim().indexOf('/')>0 ) {
			delimiter = "/ ";
		} else if(date.trim().indexOf('-')>0) {
			delimiter = "- ";
		} else if(date.trim().indexOf('.')>0) {
			delimiter = ". ";
		} else if(date.trim().indexOf(':')>0) {
			delimiter = ": ";
		}
		if(delimiter.trim().length()>0) {
			String dd = "";
			String mm = "";
			String yy = "";
			java.util.StringTokenizer tok = new java.util.StringTokenizer(date,delimiter);
			if(tok.hasMoreTokens()) {
				dd = tok.nextToken();
			}
			if(tok.hasMoreTokens()) {
				mm = tok.nextToken();
			}
			if(tok.hasMoreTokens()) {
				yy = tok.nextToken();
			}
			if(tok.hasMoreTokens()) {
				String timestr = tok.nextToken();
				String pm = "";
				if(tok.hasMoreTokens()) {
					pm = tok.nextToken();
				}
				delimiter = "";
				if(timestr.trim().indexOf(':')>0) {
					delimiter = ":.";
				} else if(timestr.trim().indexOf('.')>0) {
					delimiter = ".";
				} else if(timestr.trim().indexOf('/')>0 ) {
					delimiter = "/.";
				} else if(timestr.trim().indexOf('-')>0) {
					delimiter = "-.";
				}
				if(delimiter.trim().length()>0) {
					tok = new java.util.StringTokenizer(timestr,delimiter);
					String hrs = "0";
					String min = "0";
					String sec = "0";
					String mil = "0";
					if(tok.hasMoreTokens()) {
						hrs = tok.nextToken();
					}
					if(tok.hasMoreTokens()) {
						min = tok.nextToken();
					}
					if(tok.hasMoreTokens()) {
						sec = tok.nextToken();
					}
					if(tok.hasMoreTokens()) {
						mil = tok.nextToken();
					}
					if(tok.hasMoreTokens()) {
						pm = tok.nextToken();
					}
					if("pm".equalsIgnoreCase(mil.trim())) {
						pm = mil.trim();
					}
					try { 
						hour = Integer.parseInt(getDigits(hrs)); 
					} catch(Exception ex) { hour = 0; }
					try { 
						minute = Integer.parseInt(getDigits(min)); 
					} catch(Exception ex) { minute = 0; }
					try { 
						second = Integer.parseInt(getDigits(sec)); 
					} catch(Exception ex) { second = 0; }
					try { 
						mili = Integer.parseInt(getDigits(mil)); 
					} catch(Exception ex) { mili = 0; }
					if("pm".equalsIgnoreCase(pm)) {
						hour = hour+12;
					}
				}
			}
			try {
				day = Integer.parseInt(getDigits(dd));
				month = Integer.parseInt(getDigits(mm));
				year = Integer.parseInt(getDigits(yy));
				if(dd.length()==4) {
					year = Integer.parseInt(getDigits(dd));
					day = Integer.parseInt(getDigits(yy));
					if(month>12) {
						month = Integer.parseInt(getDigits(yy));
						day = Integer.parseInt(getDigits(mm));
					}
				} 			
				if(mm.length()==4) {
					year = Integer.parseInt(getDigits(mm));
					month = Integer.parseInt(getDigits(dd));
					day = Integer.parseInt(getDigits(yy));
					if(month>12) {
						month = Integer.parseInt(getDigits(yy));
						day = Integer.parseInt(getDigits(dd));
					}
				}
			}catch(Exception e) {
				return null;
			}
		} else {
			//date format in yyyyMMdd
			day = getDateFromYMD(date);
			month = getMonthFromYMD(date);
			year = getYearFromYMD(date);
		}
		StringBuilder buf = new StringBuilder("");
		buf.append(Integer.toString(day));
		buf.append('/');
		buf.append(Integer.toString(month));
		buf.append('/');
		buf.append(Integer.toString(year));
		if(!isDate(buf.toString())) {
			return null;
		}
		//by defualt, month begin at 0-January, 1-February ...
		if((day>0) && (month>0) && (year>0)) {
			String yrs = String.valueOf(year);
			if(yrs.length()<=2) {
				int shortyear = 35; //if short year over 35 may it be bug  
				int longyear = 2500; //if long year over 2500 may it be incorrect
				int yr = calendar.get(java.util.Calendar.YEAR);
				if(yrs.length()<2) {
					yrs = "0".concat(yrs);
				}
				if(year>shortyear) { //AD.
					if(yr<=longyear) { 
						yr = yr + 543;
						year = Integer.parseInt((String.valueOf(yr)).substring(0,2)+yrs);
						year = year - 543;
					} else {
						year = Integer.parseInt((String.valueOf(yr)).substring(0,2)+yrs);
					}
				} else {
					if(yr<=longyear) { 
						year = Integer.parseInt((String.valueOf(yr)).substring(0,2)+yrs);
					} else {
						yr = yr - 543;
						year = Integer.parseInt((String.valueOf(yr)).substring(0,2)+yrs);
						year = year + 543;
					}
				}
			}
			calendar.set(year,month-1,day,hour,minute,second);
			calendar.set(java.util.Calendar.MILLISECOND,mili);
		} else {
			return null;	
		}
		return calendar;
	}
	
	public static java.math.BigDecimal parseDecimal(String value) {
		java.math.BigDecimal result = null;
	   	String text = removeCommaText(value);
	   	if(text!=null) {
			text = getDigits(text);
			if(text.trim().length()>0) {
	         	try {
	            	java.math.BigDecimal signature = new java.math.BigDecimal(1);
	            	int pos = text.indexOf('+');
	            	if(pos>=0) {
	             	  text =  text.substring(pos+1);
	            	}
	            	if(pos<0) {
	             		pos = text.indexOf('-');
	               		if(pos>=0) {
	                  		text = text.substring(pos+1);
	                  		signature = new java.math.BigDecimal(-1);
	               		}
	            	}
	            	result = new java.math.BigDecimal(text);
	            	result = result.multiply(signature);
	         	} catch(Exception ex){
	         	}
	      	}
	   	}
	   	return result;
	}
	
	public static java.time.LocalDateTime parseLocalDateTime(String date) {
		if(date==null) {
			return null;
		}
		if(date.trim().length()<=0) {
			return null;
		}
		java.time.LocalDateTime result = java.time.LocalDateTime.now();
		int day = 0;
		int month = 0;
		int year = 0;
		int hour = 0;
		int minute = 0;
		int second = 0;
		int mili = 0;
		String delimiter = "";
		//date in format dd/MM/yyyy, yyyy/MM/dd
		if(date.trim().indexOf('/')>0 ) {
			delimiter = "/ ";
		} else if(date.trim().indexOf('-')>0) {
			delimiter = "- ";
		} else if(date.trim().indexOf('.')>0) {
			delimiter = ". ";
		} else if(date.trim().indexOf(':')>0) {
			delimiter = ": ";
		}
		if(delimiter.trim().length()>0) {
			String dd = "";
			String mm = "";
			String yy = "";
			java.util.StringTokenizer tok = new java.util.StringTokenizer(date,delimiter);
			if(tok.hasMoreTokens()) {
				dd = tok.nextToken();
			}
			if(tok.hasMoreTokens()) {
				mm = tok.nextToken();
			}
			if(tok.hasMoreTokens()) {
				yy = tok.nextToken();
			}
			if(tok.hasMoreTokens()) {
				String timestr = tok.nextToken();
				String pm = "";
				if(tok.hasMoreTokens()) {
					pm = tok.nextToken();
				}
				delimiter = "";
				if(timestr.trim().indexOf(':')>0) {
					delimiter = ":.";
				} else if(timestr.trim().indexOf('.')>0) {
					delimiter = ".";
				} else if(timestr.trim().indexOf('/')>0 ) {
					delimiter = "/.";
				} else if(timestr.trim().indexOf('-')>0) {
					delimiter = "-.";
				}
				if(delimiter.trim().length()>0) {
					tok = new java.util.StringTokenizer(timestr,delimiter);
					String hrs = "0";
					String min = "0";
					String sec = "0";
					String mil = "0";
					if(tok.hasMoreTokens()) {
						hrs = tok.nextToken();
					}
					if(tok.hasMoreTokens()) {
						min = tok.nextToken();
					}
					if(tok.hasMoreTokens()) {
						sec = tok.nextToken();
					}
					if(tok.hasMoreTokens()) {
						mil = tok.nextToken();
					}
					if(tok.hasMoreTokens()) {
						pm = tok.nextToken();
					}
					if("pm".equalsIgnoreCase(mil.trim())) {
						pm = mil.trim();
					}
					try { 
						hour = Integer.parseInt(getDigits(hrs)); 
					} catch(Exception ex) { hour = 0; }
					try { 
						minute = Integer.parseInt(getDigits(min)); 
					} catch(Exception ex) { minute = 0; }
					try { 
						second = Integer.parseInt(getDigits(sec)); 
					} catch(Exception ex) { second = 0; }
					try { 
						mili = Integer.parseInt(getDigits(mil)); 
					} catch(Exception ex) { mili = 0; }
					if("pm".equalsIgnoreCase(pm)) {
						hour = hour+12;
					}
				}
			}
			try {
				day = Integer.parseInt(getDigits(dd));
				month = Integer.parseInt(getDigits(mm));
				year = Integer.parseInt(getDigits(yy));
				if(dd.length()==4) {
					year = Integer.parseInt(getDigits(dd));
					day = Integer.parseInt(getDigits(yy));
					if(month>12) {
						month = Integer.parseInt(getDigits(yy));
						day = Integer.parseInt(getDigits(mm));
					}
				} 			
				if(mm.length()==4) {
					year = Integer.parseInt(getDigits(mm));
					month = Integer.parseInt(getDigits(dd));
					day = Integer.parseInt(getDigits(yy));
					if(month>12) {
						month = Integer.parseInt(getDigits(yy));
						day = Integer.parseInt(getDigits(dd));
					}
				}
			}catch(Exception e) {
				return null;
			}
		} else {
			//date format in yyyyMMdd
			day = getDateFromYMD(date);
			month = getMonthFromYMD(date);
			year = getYearFromYMD(date);
		}
		StringBuilder buf = new StringBuilder("");
		buf.append(Integer.toString(day));
		buf.append('/');
		buf.append(Integer.toString(month));
		buf.append('/');
		buf.append(Integer.toString(year));
		if(!isDate(buf.toString())) {
			return null;
		}		
		if((day>0) && (month>0) && (year>0)) {
			String yrs = String.valueOf(year);
			if(yrs.length()<=2) {
				int shortyear = 35; //if short year over 35 may it be bug  
				int longyear = 2500; //if long year over 2500 may it be incorrect
				int yr = result.getYear();
				if(yrs.length()<2) {
					yrs = "0".concat(yrs);
				}
				if(year>shortyear) { //AD.
					if(yr<=longyear) { 
						yr = yr + 543;
						year = Integer.parseInt((String.valueOf(yr)).substring(0,2)+yrs);
						year = year - 543;
					} else {
						year = Integer.parseInt((String.valueOf(yr)).substring(0,2)+yrs);
					}
				} else {
					if(yr<=longyear) { 
						year = Integer.parseInt((String.valueOf(yr)).substring(0,2)+yrs);
					} else {
						yr = yr - 543;
						year = Integer.parseInt((String.valueOf(yr)).substring(0,2)+yrs);
						year = year + 543;
					}
				}
			}
			if(hour==0) hour = result.getHour();
			if(minute==0) minute = result.getMinute();
			if(second==0) second = result.getSecond();
			if(mili==0) mili = result.getNano();
			result = java.time.LocalDateTime.of(year,month,day,hour,minute,second,mili);
		} else {
			return null;	
		}
		return result;
	}
	
	public static java.sql.Date parseSQLDate(String date) {
		java.time.LocalDateTime localDateTime = parseLocalDateTime(date);
		if(localDateTime!=null) {
			java.sql.Timestamp stamp = java.sql.Timestamp.valueOf(localDateTime);
			return new java.sql.Date(stamp.getTime());
		}
		return null;
	}
	
	public static java.sql.Time parseTime(String times) {
		if(times!=null && times.trim().length()>0) {
			java.sql.Time result = null;
			try {
				result = java.sql.Time.valueOf(times);
			} catch(Exception ex) { }
			String delimiter = "";
			if(times.trim().indexOf(':')>0) {
				delimiter = ":.";
			} else if(times.trim().indexOf('.')>0) {
				delimiter = ".";
			} else if(times.trim().indexOf('/')>0 ) {
				delimiter = "/.";
			} else if(times.trim().indexOf('-')>0) {
				delimiter = "-.";
			}
			if(delimiter.trim().length()>0) {
				java.util.Calendar calendar = getCalendar();
				int hour = 0;
				int minute = 0;
				int second = 0;
				int mili = 0;
				String hrs = "0";
				String min = "0";
				String sec = "0";
				String mil = "0";
				java.util.StringTokenizer tok = new java.util.StringTokenizer(times,delimiter);
				if(tok.hasMoreTokens()) {
					hrs = tok.nextToken();
				}
				if(tok.hasMoreTokens()) {
					min = tok.nextToken();
				}
				if(tok.hasMoreTokens()) {
					sec = tok.nextToken();
				}
				if(tok.hasMoreTokens()) {
					mil = tok.nextToken();
				}
				try { 
					hour = Integer.parseInt(getDigits(hrs)); 
				} catch(Exception ex) { hour = 0; }
				try { 
					minute = Integer.parseInt(getDigits(min)); 
				} catch(Exception ex) { minute = 0; }
				try { 
					second = Integer.parseInt(getDigits(sec)); 
				} catch(Exception ex) { second = 0; }
				try { 
					mili = Integer.parseInt(getDigits(mil)); 
				} catch(Exception ex) { mili = 0; }
				calendar.set(java.util.Calendar.HOUR_OF_DAY,hour);
				calendar.set(java.util.Calendar.MINUTE,minute);
				calendar.set(java.util.Calendar.SECOND,second);
				calendar.set(java.util.Calendar.MILLISECOND,mili);
				result = new java.sql.Time(calendar.getTime().getTime());
			}
			return result;
		}
		return null;
	}
	
	public static java.sql.Time parseTime(String times,String picture) {
		if(picture==null || picture.trim().length()<=0) {
			return parseTime(times);
		}
		java.sql.Time result = null;
		try {
			java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(picture);
			java.util.Date date = format.parse(times);
			result = new java.sql.Time(date.getTime());
		}catch(Exception ex) { }
		return result;
	}
	
	public static java.sql.Timestamp parseTimestamp(String timestamp) {
		java.util.Calendar calendar = getCalendar(timestamp);
		if(calendar!=null) {
			return new java.sql.Timestamp(calendar.getTime().getTime());
		}
		return null;
	}
	
	public static String removeCommaText(String text) {
		if(text==null) {
			return null;
		}
	   	if(text.trim().length()<=0) {
	   		return text;
	   	}	   	
	   	StringBuilder result = new StringBuilder("");
	   	for(java.util.StringTokenizer token = new java.util.StringTokenizer(text,",");token.hasMoreTokens();) {
	      	result.append(token.nextToken());
	   	}
	   	return result.toString();
	}
	
	public static java.util.Calendar rollingDate(java.util.Calendar calendar,int year,int month,int day,int hour,int minute,int second) {
		if(calendar!=null) {
			int y = calendar.get(java.util.Calendar.YEAR);
			int m = calendar.get(java.util.Calendar.MONTH);
			int d = calendar.get(java.util.Calendar.DAY_OF_MONTH);
			int hrs = calendar.get(java.util.Calendar.HOUR_OF_DAY);
			int min = calendar.get(java.util.Calendar.MINUTE);
			int sec = calendar.get(java.util.Calendar.SECOND);
			if(year!=0) {
				calendar.set(java.util.Calendar.YEAR,y+year);
			}
			if(month!=0) {
				calendar.set(java.util.Calendar.MONTH,m+month);
			}
			if(day!=0) {
				calendar.set(java.util.Calendar.DAY_OF_MONTH,d+day);
			}
			if(hour!=0) {
				calendar.set(java.util.Calendar.HOUR_OF_DAY,hrs+hour);
			}
			if(minute!=0) {
				calendar.set(java.util.Calendar.MINUTE,min+minute);
			}
			if(second!=0) {
				calendar.set(java.util.Calendar.SECOND,sec+second);
			}
		}
		return calendar;
	}
	
	public static java.util.Date rollingDate(java.util.Date date,int year,int month,int day,int hour,int minute,int second) {
		if(date!=null) {
			java.util.Calendar calendar = getCalendar();
			calendar.setTime(date);
			calendar = rollingDate(calendar,year,month,day,hour,minute,second);
			return calendar.getTime();			
		}
		return date;
	}
	
	public static java.sql.Time rollingTime(java.sql.Time time,int hour,int minute,int second) {
		java.util.Date date = rollingDate(time, 0, 0, 0, hour, minute, second);
		return date==null?null:new java.sql.Time(date.getTime());
	}
	
	public static java.sql.Time rollingTime(String timestr,int hour,int minute,int second) {
		return rollingTime(parseTime(timestr),hour,minute,second);
	}
	
}
