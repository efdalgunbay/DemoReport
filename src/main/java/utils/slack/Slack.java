package utils.slack;

import api_assured.ApiUtilities;
import api_assured.ServiceGenerator;
import models.slack.SimpleMessageModel;
import models.slack.PostMessage;
import models.slack.ThreadMessageModel;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import utils.Helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Slack extends ApiUtilities {
    SlackServices slackServices = new ServiceGenerator(new Headers.Builder()
            .add("Authorization", Helper.testProp.getProperty("bot-user-oauth"))
            .build())
            .generate(SlackServices.class);

    public PostMessage postSimpleMessage(String message, String channelId) {
        Call<PostMessage> messageCall = slackServices.postMessage(
                new SimpleMessageModel(channelId, "mrkdwn", message)
        );
        return perform(messageCall, false, false);
    }


    public Object postThreadMessage(String message, String channelId, String threadTs) {
        Call<Object> messageCall = slackServices.postThreadMessage(
                new ThreadMessageModel(channelId, "mrkdwn", message, threadTs)
        );
        return perform(messageCall, false, false);
    }

    public Object postMultipartThreadMessage(File file, String comments, String channelId, String threadTs) {
        String mediaType;
        try {
            mediaType = Files.probeContentType(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        RequestBody fileBody = RequestBody.create(MediaType.parse(mediaType), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), fileBody);
        Call<Object> postFile = slackServices.postMultipartThreadMessage(
                part,
                RequestBody.create(MediaType.parse("text/plain"), comments),
                RequestBody.create(MediaType.parse("text/plain"), channelId),
                RequestBody.create(MediaType.parse("text/plain"), threadTs)
        );
        return perform(postFile, false, false);
    }


    public Object postMultipartMessage(File file, String comments, String channelId) {
        String mediaType;
        try {
            mediaType = Files.probeContentType(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        RequestBody fileBody = RequestBody.create(MediaType.parse(mediaType), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), fileBody);
        Call<Object> postFile = slackServices.postMultipartMessage(
                part,
                RequestBody.create(MediaType.parse("text/plain"), comments),
                RequestBody.create(MediaType.parse("text/plain"), channelId)
        );
        return perform(postFile, false, false);
    }
}
