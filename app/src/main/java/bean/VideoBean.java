package bean;

import java.util.List;

public class VideoBean {

    public List<ItemListDTO> itemList;
    public int count;
    public int total;
    public String nextPageUrl;
    public boolean adExist;

    public List<ItemListDTO> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemListDTO> itemList) {
        this.itemList = itemList;
    }

    public static class ItemListDTO {
        public String type;
        public DataDTO data;
        public Object trackingData;
        public Object tag;
        public int id;
        public int adIndex;

        public static class DataDTO {
            public String dataType;
            public int id;
            public String title;
            public String description;
            public String library;
            public List<TagsDTO> tags;
            public ConsumptionDTO consumption;
            public String resourceType;
            public Object slogan;
            public ProviderDTO provider;
            public String category;
            public AuthorDTO author;
            public CoverDTO cover;
            public String playUrl;
            public Object thumbPlayUrl;
            public int duration;
            public WebUrlDTO webUrl;
            public long releaseTime;
            public List<PlayInfoDTO> playInfo;
            public Object campaign;
            public Object waterMarks;
            public boolean ad;
            public List<?> adTrack;
            public String type;
            public String titlePgc;
            public String descriptionPgc;
            public String remark;
            public boolean ifLimitVideo;
            public int searchWeight;
            public Object brandWebsiteInfo;
            public Object videoPosterBean;
            public int idx;
            public Object shareAdTrack;
            public Object favoriteAdTrack;
            public Object webAdTrack;
            public long date;
            public Object promotion;
            public Object label;
            public List<?> labelList;
            public String descriptionEditor;
            public boolean collected;
            public boolean reallyCollected;
            public boolean played;
            public List<?> subtitles;
            public Object lastViewTime;
            public Object playlists;
            public Object src;
            public Object recallSource;
            public Object recall_source;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getPlayUrl() {
                return playUrl;
            }

            public void setPlayUrl(String playUrl) {
                this.playUrl = playUrl;
            }

            public AuthorDTO getAuthor() {
                return author;
            }

            public void setAuthor(AuthorDTO author) {
                this.author = author;
            }

            public CoverDTO getCover() {
                return cover;
            }

            public void setCover(CoverDTO cover) {
                this.cover = cover;
            }

            public static class ConsumptionDTO {
                public int collectionCount;
                public int shareCount;
                public int replyCount;
                public int realCollectionCount;
            }

            public static class ProviderDTO {
                public String name;
                public String alias;
                public String icon;
            }

            public static class AuthorDTO {
                public int id;
                public String icon;
                public String name;
                public String description;
                public String link;
                public long latestReleaseTime;
                public int videoNum;
                public Object adTrack;
                public FollowDTO follow;
                public ShieldDTO shield;
                public int approvedNotReadyVideoCount;
                public boolean ifPgc;
                public int recSort;
                public boolean expert;

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public static class FollowDTO {
                    public String itemType;
                    public int itemId;
                    public boolean followed;
                }

                public static class ShieldDTO {
                    public String itemType;
                    public int itemId;
                    public boolean shielded;
                }
            }

            public static class CoverDTO {
                public String feed;
                public String detail;
                public String blurred;
                public Object sharing;
                public Object homepage;

                public String getDetail() {
                    return detail;
                }

                public void setDetail(String detail) {
                    this.detail = detail;
                }
            }

            public static class WebUrlDTO {
                public String raw;
                public String forWeibo;
            }

            public static class TagsDTO {
                public int id;
                public String name;
                public String actionUrl;
                public Object adTrack;
                public Object desc;
                public String bgPicture;
                public String headerImage;
                public String tagRecType;
                public Object childTagList;
                public Object childTagIdList;
                public boolean haveReward;
                public boolean ifNewest;
                public Object newestEndTime;
                public int communityIndex;
            }

            public static class PlayInfoDTO {
                public int height;
                public int width;
                public List<UrlListDTO> urlList;
                public String name;
                public String type;
                public String url;

                public static class UrlListDTO {
                    public String name;
                    public String url;
                    public int size;
                }
            }
        }
    }
}
