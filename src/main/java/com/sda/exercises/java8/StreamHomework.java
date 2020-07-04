package com.sda.exercises.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamHomework {
    public static void main(String[] args) {
        List<Season> series = new ArrayList<>();

        // żeby dostać listę odcinków, możemy wydobyć z każdej serii strumień odcinkóœ
        // stream pozwala łatwo łączyć strumienie w jeden większy przez flatMap()
        List<Episode> episodes = series.stream()
                .flatMap(season -> season.episodes.stream())
                .collect(Collectors.toList());

        // po kolei schodzimy co raz niżej w hierarchii obiektów,
        // filtrujemy sezony, z pozostałych wydobywamy strumień odcinków
        // filtrujemy odcinki, z pozostalych wydobywamy strumień Video
        // filtrujemy Video i w wyniku dostajemy listę spełniającą wszystkie warunki
        List<Video> collect = series.stream()
                .filter(season -> season.seasonNumber % 2 == 0)
                .flatMap(season -> season.episodes.stream())
                .filter(episode -> episode.episodeNumber % 2 == 1)
                .flatMap(episode -> episode.videos.stream())
                .filter(video -> VideoType.PREVIEW.equals(video.videoType))
                .collect(Collectors.toList());
    }
}

enum VideoType {
    CLIP, PREVIEW, EPISODE
}

class Video {
    public String title;
    public String url;
    public VideoType videoType;

    public Video(String title, String url, VideoType videoType) {
                this.videoType = videoType;
    }
}

class Episode {
    public String episodeName;
    public int episodeNumber;
    List<Video> videos;

    public Episode(String episodeName, int episodeNumber, List<Video> videos) {
        this.episodeName = episodeName;
        this.episodeNumber = episodeNumber;
        this.videos = videos;
    }
}


class Season {
    public String seasonName;
    public int seasonNumber;
    List<Episode> episodes;

    public Season(String seasonName, int seasonNumber, List<Episode> episodes) {
        this.seasonName = seasonName;
        this.seasonNumber = seasonNumber;
        this.episodes = episodes;
    }
}

