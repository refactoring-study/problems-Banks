package sns.feed;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import sns.feed.feed.domain.FacebookFeed;
import sns.feed.feed.domain.GoplFeed;
import sns.feed.feed.domain.IFeed;
import sns.feed.feed.domain.TwitterFeed;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class FeedStorage {

    private static FeedStorage instace;

    private List<IFeed> feeds;

    private FeedStorage() {
        feeds = Collections.synchronizedList(new ArrayList<IFeed>());
        init();
    }

    public static FeedStorage getInstance() {
        if (instace == null) {
            instace = new FeedStorage();
        }

        return instace;
    }

    private void init() {
        File jsonDir = new File("./src/main/resources/sns/feed/collector/feed");

        feeds.addAll(getFacebookFeed(jsonDir));
        feeds.addAll(getGoplFeed(jsonDir));
        feeds.addAll(getTwitterFeed(jsonDir));

    }

    private List<FacebookFeed> getFacebookFeed(File jsonDir) {
        File facebookJson = new File(jsonDir.getAbsoluteFile() + "/facebookfeed.json");
        StringBuffer buffer = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(facebookJson));
            String line;
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Date.class, new YMDDateTypeAdapter());
            FacebookFeed[] feed = builder.create().fromJson(buffer.toString(), FacebookFeed[].class);
            return (Arrays.asList(feed));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return Collections.emptyList();
    }

    private List<GoplFeed> getGoplFeed(File jsonDir) {
        File facebookJson = new File(jsonDir.getAbsoluteFile() + "/goplfeed.json");
        StringBuffer buffer = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(facebookJson));
            String line;
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Date.class, new YMDDateTypeAdapter());
            GoplFeed[] feed = builder.create().fromJson(buffer.toString(), GoplFeed[].class);
            return (Arrays.asList(feed));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return Collections.emptyList();
    }

    private List<TwitterFeed> getTwitterFeed(File jsonDir) {
        File facebookJson = new File(jsonDir.getAbsoluteFile() + "/twitterfeed.json");
        StringBuffer buffer = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(facebookJson));
            String line;
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Date.class, new YMDDateTypeAdapter());
            TwitterFeed[] feed = builder.create().fromJson(buffer.toString(), TwitterFeed[].class);
            return (Arrays.asList(feed));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return Collections.emptyList();
    }

    public void addFeed(IFeed snsAccount) {
        feeds.add(snsAccount);
    }

    public void removeFeed(IFeed snsAccount) {
        feeds.remove(snsAccount);
    }

    public IFeed get(int index) {
        return feeds.get(index);
    }

    public List<IFeed> getAllFeeds() {
        return Collections.unmodifiableList(feeds);
    }

    public List<IFeed> getFacebookFeeds() {

        List<IFeed> facebookFeed = getFeedsForType(FacebookFeed.class);

        return facebookFeed;
    }

    public List<IFeed> getGoplFeeds() {

        List<IFeed> goplFeed = getFeedsForType(GoplFeed.class);

        return goplFeed;
    }

    public List<IFeed> getTwitterFeeds() {

        List<IFeed> twitterFeed = getFeedsForType(TwitterFeed.class);

        return twitterFeed;
    }

    private List<IFeed> getFeedsForType(Class<? extends IFeed> args) {

        List<IFeed> filteredFeed = new ArrayList<IFeed>();
        for (int idx = 0, size = feeds.size(); idx < size; ++idx) {
            if (feeds.get(idx).getClass() == args) {
                filteredFeed.add(feeds.get(idx));
            }
        }

        return filteredFeed;
    }

    public List<IFeed> getFeedsSortByCreatedDate() {
        List<IFeed> modifiedDatas = new ArrayList<IFeed>(feeds.size());
        Collections.copy(modifiedDatas, feeds);

        Collections.sort(modifiedDatas, new Comparator<IFeed>() {

            public int compare(IFeed o1, IFeed o2) {

                return (int) ((o1.getCreated()).getTime() - (o2.getCreated()).getTime());
            }
        });

        return modifiedDatas;

    }

    public void release() {
        synchronized (feeds) {
            feeds.clear();
        }

        synchronized (instace) {
            instace = null;
        }
    }

    private static class YMDDateTypeAdapter implements JsonDeserializer<Date> {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                return dateFormat.parse(json.getAsString());
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                return null;
            }
        }

    }
}
