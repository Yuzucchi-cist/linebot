package jp.cist.keion.linebot;

import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import jp.cist.keion.linebot.models.ClubMember;
import jp.cist.keion.linebot.replier.Follow;
import jp.cist.keion.linebot.replier.Parrot;
import jp.cist.keion.linebot.repositoris.ClubMemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@LineMessageHandler
public class Callback {

    private static final Logger log = LoggerFactory.getLogger(Callback.class);

    @Autowired
    ClubMemberRepository cmRepository;
    // フォローイベントに対応する
    @EventMapping
    public Message handleFollow(FollowEvent event) {
        // 実際はこのタイミングでフォロワーのユーザIDをデータベースにに格納しておくなど
        Follow follow = new Follow(event, cmRepository);
        return follow.reply();
    }

    @EventMapping
    public Message handleMessage(MessageEvent<TextMessageContent> event) {
        TextMessageContent message = event.getMessage();
        String text = message.getText();
        switch (text) {
            case "id":
                Iterable<ClubMember> members = cmRepository.findAll();
                String replyText="ids:";
                for (ClubMember member : members) {
                    replyText += member.getLineid();
                }
                return new TextMessage(replyText);
            default:
                Parrot parrot = new Parrot(event);
                return parrot.reply();
        }
    }

}
