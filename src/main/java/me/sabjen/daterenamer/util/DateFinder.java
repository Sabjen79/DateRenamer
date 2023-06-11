package me.sabjen.daterenamer.util;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

public class DateFinder {
    private static final int[] types = new int[]{
            36867, 1286, 102, 306
    };

    private static final String[] formats = new String[]{
            "yyyy:MM:dd HH:mm:ss",
            "yyyy-MM-dd'T'HH:mm:ssXX",
            "EEE MMM dd HH:mm:ss zzzz yyyy",
            "yyyy:MM:dd HH:mm:ss"
    };

    public static Date getDate(File file) {
        AtomicReference<Date> date = new AtomicReference<>(null);

        try {
            Metadata metadata = ImageMetadataReader.readMetadata(file.getAbsoluteFile());

            for(int i = 0; i < formats.length; i++) {
                int type = types[i];
                String format = formats[i];

                metadata.getDirectories().forEach(directory -> {
                    if(date.get() != null) return;

                    if(directory.containsTag(type)) {
                        try {
                            date.set(new SimpleDateFormat(format).parse(directory.getString(type)));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                });

                if(date.get() != null) break;
            }

        } catch (ImageProcessingException | IOException e) {
            return new Date(file.lastModified());
        }

        if(date.get() == null) return new Date(file.lastModified());
        return date.get();
    }
}
