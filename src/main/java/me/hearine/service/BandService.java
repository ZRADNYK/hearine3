package me.hearine.service;

import me.hearine.controller.ControllerUtils;
import me.hearine.domain.Album;
import me.hearine.domain.Band;
import me.hearine.repos.BandRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
public class BandService {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private BandRepo bandRepo;

    public List<Band> findAll() {
        return bandRepo.findAll();
    }

    public Band findByName(String name) {
        return bandRepo.findByName(name);
    }

    public Set<Band> findByAlbumsContaining(Album album) {
        return bandRepo.findByAlbumsContaining(album);
    }


    public boolean addArtist(Band band) {
        Band bandFromDb = bandRepo.findByName(band.getName());

        if (band != null) {
            return false;
        }

        // other stuff if needed

        bandRepo.save(band);

        return true;
    }

    public void save(Band band, String name) {
        band.setName(name);
     /*   try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
            java.util.Date date = sdf1.parse(DOB);
            java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
            band.setDOB(sqlStartDate);
        }
        catch (ParseException ex) {
            ex.printStackTrace();
            band.setDOB(null);
        }*/
        //       band.setAvatar(ControllerUtils.saveFile(file, uploadPath));

        bandRepo.save(band);
    }

    public void saveRawArtist(Band band, String name) {
        band.setName(name);
        bandRepo.save(band);
    }
}
