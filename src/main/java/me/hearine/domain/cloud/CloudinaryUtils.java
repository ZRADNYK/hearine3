package me.hearine.domain.cloud;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.zip.DataFormatException;

public class CloudinaryUtils {

    private static final String[] imageExtensions = new String[]{"jpg", "jpeg", "png"};
    private static final String[] audioExtensions = new String[] {"mp3", "wmv",};

    private static final String imageType = "image";
    private static final String audioType = "video"; // due to Cloudinary restrictions
    private static final String unknownType = "unknown";

    private static final String imagePath = "hearine_images_data/";
    private static final String audioPath = "hearine_songs_data/";

    private static Cloudinary setCloudinary() {
        HashMap config = new HashMap();
        config.put("cloud_name", "hearine");
        config.put("api_key", "371689331281971");
        config.put("api_secret", "j9_Hfisjv9dLPsqKwIeqnlzDfqk");
        return new Cloudinary(config);
    }

    public static String uploadFileToCloud(MultipartFile file, String name) throws Exception {
        String tempName = file.getOriginalFilename();
        String fileType = getFileType(tempName);
        if(!fileType.equals(unknownType)) {
            Cloudinary cloudinary = setCloudinary();
            byte[] byteArr = file.getBytes();
            Map response = new HashMap();
            if (fileType.equals(audioType)) {
                response = cloudinary.uploader().upload(byteArr,
                        ObjectUtils.asMap("resource_type", fileType,
                                "public_id", audioPath + name));
            }
            else if(fileType.equals(imageType)) {
                response = cloudinary.uploader().upload(byteArr,
                        ObjectUtils.asMap("resource_type", fileType,
                                "public_id", imagePath + name));
            }
            return (String) response.get("url");
        }
        else throw new DataFormatException("Uploaded file has incorrect extension!");
    }

    private static String getFileType(String name) {
        String extension = FilenameUtils.getExtension(name);
        for (String imageExtension : imageExtensions) {
            if (extension.equals(imageExtension)) {
                return imageType;
            }
        }
        for (String audioExtension : audioExtensions) {
            if (extension.equals(audioExtension)) {
                return audioType;
            }
        }
        return unknownType;
    }
}
