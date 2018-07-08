package com.bms.common;


public class Constants {

	public static final int NOT_EXIST = 0;
	public static final int EXIST = 1;
	
	public static final int NOT_VERIFIED = 0;
	public static final int VERIFIED = 1;
	
	public static final int ACTIVE = 1;
	public static final int INACTIVE = 0;
	
	public static final int PRIMARY = 1;
	public static final int SECONDARY = 0;
	
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

}
