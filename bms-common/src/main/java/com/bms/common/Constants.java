package com.bms.common;


public class Constants {

	public static final int STATUS_NOT_EXIST = 0;
	public static final int STATUS_EXIST = 1;

	public static final int PLATFORM_ANDROID = 1;
	public static final int PLATFORM_IOS = 2;
	public static final int PLATFORM_WEB = 3;
	public static final int PLATFORM_DESKTOP = 4;

	public static final int ACCESS_VIEW = 0;
	public static final int ACCESS_MODIFY = 3;
	public static final int ACCESS_CREATE = 6;
	public static final int ACCESS_REMOVE = 9;

	public static final int IMAGE_TYPE_PROFILE = 1;
	public static final int IMAGE_TYPE_COVER = 2;
	public static final int IMAGE_TYPE_OTHER = 0;
	
	public static final int SOCIAL_MEDIA_FACEBOOK = 1;
	public static final int SOCIAL_MEDIA_TWITTER = 2;
	public static final int SOCIAL_MEDIA_GOOGLE_PLUS = 3;
	
	public static final int MALE = 1;
	public static final int FEMALE = 2;
	
	public static final int VALUE_GROUP_ADMIN = 1;
	public static final int VALUE_GROUP_CLIENT = 2;
	
	public static final int VALUE_ROLE_SUPER_ADMIN = 1;
	public static final int VALUE_ROLE_MASTER_ADMIN = 2;
	public static final int VALUE_ROLE_ADMIN = 3;
	public static final int VALUE_ROLE_CONSUMER = 4;
	public static final int VALUE_ROLE_VISITOR = 5;
	
	public static final int AMENITY_COMMON = 0;
	public static final int AMENITY_ADITIONAL = 1;
	
	public static final String DIR_UPLOADED_FILES = "uploadDirectory";
	public static final String FOLDER_PROFILE_IMAGE = "ProfileImage";
	public static final String FOLDER_COVER_IMAGE = "CoverImage";
	public static final String FOLDER_OTHER_IMAGE = "OtherImage";
	public static final String FOLDER_TEMP_FILES = "tempfiles";
	
	public static final String IMAGE_THUMB = "thumb";
	public static final String IMAGE_CROP = "crp";
	public static final String IMAGE_200 = "200";
	public static final String IMAGE_600 = "600";
	
	public static final int MAX_UPLOAD_FILE_SIZE = 10 * 1024 * 1024;
	public static final int MAX_UPLOAD_FILE_MEMORY_SIZE = 2 * 1024 * 1024;
	
	public static final String[][] COUNTRY_CODES = {
			{"880", "bd", "Bangladesh", "1"},
			{"93", "af", "Afghanistan", "0"},                                                       
			{"355", "al", "Albania", "0"},                                                      
			{"213", "dz", "Algeria", "0"},                                                      
			{"1684", "as", "American Samoa", "0"},                                                    
			{"376", "ad", "Andorra", "0"},                                                              
			{"244", "ao", "Angola", "0"},                                                              
			{"1264", "ai", "Anguilla", "0"},                                                             
			{"1268", "ag", "Antigua and Barbuda", "0"},                                                                                               
			{"54", "ar", "Argentina", "0"},                                                                                          
			{"374", "am", "Armenia", "0"},                                                                                                  
			{"297", "aw", "Aruba", "0"},                                                                                                
			{"61", "au", "Australia", "0"},                                                                                               
			{"43", "at", "Austria", "0"},                                                                                                 
			{"994", "az", "Azerbaijan", "0"},                                                                                              
			{"1242", "bs", "Bahamas", "0"},                                                                                              
			{"973", "bh", "Bahrain", "0"},                                                                                                                                                                                        
			{"1246", "bb", "Barbados", "0"},                                                                                               
			{"375", "by", "Belarus", "0"},                                                                                        
			{"32", "be", "Belgium", "0"},                                                                                     
			{"501", "bz", "Belize", "0"},                                                                                          
			{"229", "bj", "Benin", "0"},                                                                                           
			{"1441", "bm", "Bermuda", "0"},                                                                                  
			{"975", "bt", "Bhutan", "0"},                                                                               
			{"591", "bo", "Bolivia", "0"},                                                                                                  
			{"387", "ba", "Bosnia and Herzegovina", "0"},                                                                                              
			{"267", "bw", "Botswana", "0"},                                                                                          
			{"55", "br", "Brazil", "0"},                                                                                             
			{"246", "io", "British Indian Ocean Territory", "0"},                                                                                        
			{"1284", "vg", "British Virgin Islands", "0"},                                                                                                  
			{"673", "bn", "Brunei", "0"},                                                                                                  
			{"359", "bg", "Bulgaria", "0"},                                                                                                  
			{"226", "bf", "Burkina Faso", "0"},                                                                                          
			{"257", "bi", "Burundi", "0"},                                                                                                   
			{"855", "kh", "Cambodia", "0"},                                                                                                   
			{"237", "cm", "Cameroon", "0"},                                                                                                
			{"1", "ca", "Canada", "0"},                                                                                              
			{"238", "cv", "Cape Verde", "0"},                                                                                            
			{"599", "bq", "Caribbean Netherlands", "0"},                                                                            
			{"1345", "ky", "Cayman Islands", "0"},                                                                                         
			{"236", "cf", "Central African Republic", "0"},                                                                                     
			{"235", "td", "Chad", "0"},                                                                                         
			{"56", "cl", "Chile", "0"},                                                                                
			{"86", "cn", "China", "0"},                                                                                                 
			{"61", "cx", "Christmas Island", "0"},                                                                                                 
			{"61", "cc", "Cocos (Keeling) Islands", "0"},                                                                                               
			{"57", "co", "Colombia", "0"},                                                                                      
			{"269", "km", "Comoros", "0"},                                                                                 
			{"243", "cd", "Congo (DRC)", "0"},                                                                                  
			{"242", "cg", "Congo (Republic)", "0"},                                                                                         
			{"682", "ck", "Cook Islands", "0"},                                                                                         
			{"506", "cr", "Costa Rica", "0"},                                                                                              
			{"225", "ci", "Côte d’Ivoire", "0"},                                                                                                 
			{"385", "hr", "Croatia", "0"},                                                                               
			{"53", "cu", "Cuba", "0"},                                                                                        
			{"599", "cw", "Curaçao", "0"},                                                                                          
			{"357", "cy", "Cyprus", "0"},                                                                                        
			{"420", "cz", "Czech Republic", "0"},                                                                                               
			{"45", "dk", "Denmark", "0"},                                                                                         
			{"253", "dj", "Djibouti", "0"},                                                                                                
			{"1767", "dm", "Dominica", "0"},                                                                                               
			{"1", "do", "Dominican Republic", "0"},                                                                                              
			{"593", "ec", "Ecuador", "0"},                                                                                         
			{"20", "eg", "Egypt", "0"},                                                                                                  
			{"503", "sv", "El Salvador", "0"},                                                                                         
			{"240", "gq", "Equatorial Guinea", "0"},                                                                                            
			{"291", "er", "Eritrea", "0"},                                                                                                   
			{"372", "ee", "Estonia", "0"},                                                                                                   
			{"251", "et", "Ethiopia", "0"},                                                                                                 
			{"500", "fk", "Falkland Islands", "0"},                                                                                                   
			{"298", "fo", "Faroe Islands", "0"},                                                                                            
			{"679", "fj", "Fiji", "0"},                                                                                            
			{"358", "fi", "Finland", "0"},                                                                                          
			{"33", "fr", "France", "0"},                                                                                            
			{"594", "gf", "French Guiana", "0"},                                                                                                   
			{"689", "pf", "French Polynesia", "0"},                                                                                    
			{"241", "ga", "Gabon", "0"},                                                                                  
			{"220", "gm", "Gambia", "0"},                                                                                             
			{"995", "ge", "Georgia", "0"},                                                                                               
			{"49", "de", "Germany", "0"},                                                                                           
			{"233", "gh", "Ghana", "0"},                                                                                           
			{"350", "gi", "Gibraltar", "0"},                                                                                              
			{"30", "gr", "Greece", "0"},                                                                                            
			{"299", "gl", "Greenland", "0"},                                                                                              
			{"1473", "gd", "Grenada", "0"},                                                                                                  
			{"590", "gp", "Guadeloupe", "0"},                                                                                  
			{"1671", "gu", "Guam", "0"},                                                                                                    
			{"502", "gt", "Guatemala", "0"},                                                                                                     
			{"44", "gg", "Guernsey", "0"},                                                                                             
			{"224", "gn", "Guinea", "0"},                                                                                       
			{"245", "gw", "Guinea-Bissau", "0"},                                                                                  
			{"592", "gy", "Guyana", "0"},                                                                                       
			{"509", "ht", "Haiti", "0"},                                                                                           
			{"504", "hn", "Honduras", "0"},                                                                                             
			{"852", "hk", "Hong Kong", "0"},                                                                                                  
			{"36", "hu", "Hungary", "0"},                                                                                                     
			{"354", "is", "Iceland", "0"},                                                                                                       
			{"91", "in", "India", "0"},                                                                                                   
			{"62", "id", "Indonesia", "0"},                                                                                                
			{"98", "ir", "Iran", "0"},                                                                                                    
			{"964", "iq", "Iraq", "0"},                                                                                               
			{"353", "ie", "Ireland", "0"},                                                                                        
			{"44", "im", "Isle of Man", "0"},                                                                                                  
			{"972", "il", "Israel", "0"},                                                                                                   
			{"39", "it", "Italy", "0"},                                                                                                   
			{"1876", "jm", "Jamaica", "0"},                                                                                                
			{"81", "jp", "Japan", "0"},                                                                                               
			{"44", "je", "Jersey", "0"},                                                                                                   
			{"962", "jo", "Jordan", "0"},                                                                                                
			{"7", "kz", "Kazakhstan", "0"},                                                                                               
			{"254", "ke", "Kenya", "0"},                                                                                               
			{"686", "ki", "Kiribati", "0"},                                                                                                
			{"965", "kw", "Kuwait", "0"},                                                                                                
			{"996", "kg", "Kyrgyzstan", "0"},                                                                                           
			{"856", "la", "Laos", "0"},                                                                                            
			{"371", "lv", "Latvia", "0"},                                                                                           
			{"961", "lb", "Lebanon", "0"},                                                                                          
			{"266", "ls", "Lesotho", "0"},                                                                                          
			{"231", "lr", "Liberia", "0"},                                                                                            
			{"218", "ly", "Libya", "0"},                                                                                              
			{"423", "li", "Liechtenstein", "0"},                                                                                          
			{"370", "lt", "Lithuania", "0"},                                                                                       
			{"352", "lu", "Luxembourg", "0"},                                                                                   
			{"853", "mo", "Macau", "0"},                                                                                             
			{"389", "mk", "Macedonia", "0"},                                                                                         
			{"261", "mg", "Madagascar", "0"},                                                                                            
			{"265", "mw", "Malawi", "0"},                                                                                           
			{"60", "my", "Malaysia", "0"},                                                                                                     
			{"960", "mv", "Maldives", "0"},                                                                                                   
			{"223", "ml", "Mali", "0"},                                                                                               
			{"356", "mt", "Malta", "0"},                                                                                                     
			{"692", "mh", "Marshall Islands", "0"},                                                                                                   
			{"596", "mq", "Martinique", "0"},                                                                                                   
			{"222", "mr", "Mauritania", "0"},                                                                                                  
			{"230", "mu", "Mauritius (Moris)", "0"},                                                                                                 
			{"262", "yt", "Mayotte", "0"},                                                                                                  
			{"52", "mx", "Mexico", "0"},                                                                                               
			{"691", "fm", "Micronesia", "0"},                                                                                                   
			{"373", "md", "Moldova", "0"},                                                                                            
			{"377", "mc", "Monaco", "0"},                                                                                           
			{"976", "mn", "Mongolia", "0"},                                                                                          
			{"382", "me", "Montenegro", "0"},                                                                                                  
			{"1664", "ms", "Montserrat", "0"},                                                                                             
			{"212", "ma", "Morocco", "0"},                                                                                                  
			{"258", "mz", "Mozambique", "0"},                                                                                                
			{"95", "mm", "Myanmar", "0"},                                                                                          
			{"264", "na", "Namibia", "0"},                                                                                                
			{"674", "nr", "Nauru", "0"},                                                                                             
			{"977", "np", "Nepal", "0"},                                                                                           
			{"31", "nl", "Netherlands", "0"},                                                                                                 
			{"687", "nc", "New Caledonia", "0"},                                                                                                
			{"64", "nz", "New Zealand", "0"},                                                                                                   
			{"505", "ni", "Nicaragua", "0"},                                                                                                  
			{"227", "ne", "Niger", "0"},                                                                                                   
			{"234", "ng", "Nigeria", "0"},                                                                                             
			{"683", "nu", "Niue", "0"},                                                                                                   
			{"672", "nf", "Norfolk Island", "0"},                                                                                           
			{"850", "kp", "North Korea", "0"},                                                                                           
			{"1670", "mp", "Northern Mariana Islands", "0"},                                                                             
			{"47", "no", "Norway", "0"},                                                                                              
			{"968", "om", "Oman", "0"},                                                                                                
			{"92", "pk", "Pakistan", "0"},                                                                                       
			{"680", "pw", "Palau", "0"},                                                                                          
			{"970", "ps", "Palestine", "0"},                                                                                      
			{"507", "pa", "Panama", "0"},                                                                                    
			{"675", "pg", "Papua New Guinea", "0"},                                                                          
			{"595", "py", "Paraguay", "0"},                                                                                  
			{"51", "pe", "Peru", "0"},                                                                                        
			{"63", "ph", "Philippines", "0"},                                                                                            
			{"48", "pl", "Poland", "0"},                                                                                           
			{"351", "pt", "Portugal", "0"},                                                                                              
			{"1", "pr", "Puerto Rico", "0"},                                                                                             
			{"974", "qa", "Qatar", "0"},                                                                                               
			{"262", "re", "Réunion", "0"},                                                                                               
			{"40", "ro", "Romania", "0"},                                                                                              
			{"7", "ru", "Russia", "0"},                                                                                              
			{"250", "rw", "Rwanda", "0"},                                                                                
			{"590", "bl", "Saint Barthélemy", "0"},                                                                                                    
			{"290", "sh", "Saint Helena", "0"},                                                                                             
			{"1869", "kn", "Saint Kitts and Nevis", "0"},                                                                                              
			{"1758", "lc", "Saint Lucia", "0"},                                                                                            
			{"590", "mf", "Saint Martin", "0"},                                                                                         
			{"508", "pm", "Saint Pierre and Miquelon", "0"},                                                                                              
			{"1784", "vc", "Saint Vincent and the Grenadines", "0"},                                                                                  
			{"685", "ws", "Samoa", "0"},                                                                                              
			{"378", "sm", "San Marino", "0"},                                                                                              
			{"239", "st", "São Tomé and Príncipe", "0"},                                                                                 
			{"966", "sa", "Saudi Arabia", "0"},                                                                                       
			{"221", "sn", "Senegal", "0"},                                                                                            
			{"381", "rs", "Serbia", "0"},                                                                                        
			{"248", "sc", "Seychelles", "0"},                                                                                                  
			{"232", "sl", "Sierra Leone", "0"},                                                                                        
			{"65", "sg", "Singapore", "0"},                                                                                     
			{"1721", "sx", "Sint Maarten", "0"},                                                                                                     
			{"421", "sk", "Slovakia", "0"},                                                                                               
			{"386", "si", "Slovenia", "0"},                                                                                               
			{"677", "sb", "Solomon Islands", "0"},                                                                                         
			{"252", "so", "Somalia", "0"},                                                                                             
			{"27", "za", "South Africa", "0"},                                                                                               
			{"82", "kr", "South Korea", "0"},                                                                                               
			{"211", "ss", "South Sudan", "0"},                                                                                      
			{"34", "es", "Spain", "0"},                                                                                                
			{"94", "lk", "Sri Lanka", "0"},                                                                                                   
			{"249", "sd", "Sudan", "0"},                                                                                                   
			{"597", "sr", "Suriname", "0"},                                                                                                 
			{"47", "sj", "Svalbard and Jan Mayen", "0"},                                                                                           
			{"268", "sz", "Swaziland", "0"},                                                                                       
			{"46", "se", "Sweden", "0"},                                                                                          
			{"41", "ch", "Switzerland", "0"},                                                                                               
			{"963", "sy", "Syria", "0"},                                                                                           
			{"886", "tw", "Taiwan", "0"},                                                                                                 
			{"992", "tj", "Tajikistan", "0"},                                                                                        
			{"255", "tz", "Tanzania", "0"},                                                                                               
			{"66", "th", "Thailand", "0"},                                                                                                   
			{"670", "tl", "Timor-Leste", "0"},                                                                                                      
			{"228", "tg", "Togo", "0"},                                                                                               
			{"690", "tk", "Tokelau", "0"},                                                                                                  
			{"676", "to", "Tonga", "0"},                                                                                                   
			{"1868", "tt", "Trinidad and Tobago", "0"},                                                                                    
			{"216", "tn", "Tunisia", "0"},                                                                                           
			{"90", "tr", "Turkey", "0"},                                                                                          
			{"993", "tm", "Turkmenistan", "0"},                                                                                                 
			{"1649", "tc", "Turks and Caicos Islands", "0"},                                                                                            
			{"688", "tv", "Tuvalu", "0"},                                                                                                 
			{"1340", "vi", "U.S. Virgin Islands", "0"},                                                                                                
			{"256", "ug", "Uganda", "0"},                                                                                                 
			{"380", "ua", "Ukraine", "0"},                                                                                       
			{"971", "ae", "United Arab Emirates", "0"},                                                                                         
			{"44", "gb", "United Kingdom", "0"},                                                                                        
			{"1", "us", "United States", "0"},                                                                                                   
			{"598", "uy", "Uruguay", "0"},                                                                                               
			{"998", "uz", "Uzbekistan", "0"},                                                                                              
			{"678", "vu", "Vanuatu", "0"},                                                                                                  
			{"39", "va", "Vatican City", "0"},                                                                                              
			{"58", "ve", "Venezuela", "0"},                                                                                         
			{"84", "vn", "Vietnam", "0"},                                                                                          
			{"681", "wf", "Wallis and Futuna", "0"},                                                                                            
			{"212", "eh", "Western Sahara", "0"},                                                                                     
			{"967", "ye", "Yemen", "0"},                                                                                              
			{"260", "zm", "Zambia", "0"},                                                                                                 
			{"263", "zw", "Zimbabwe", "0"}};

}
