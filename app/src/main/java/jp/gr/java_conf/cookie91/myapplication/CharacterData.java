package jp.gr.java_conf.cookie91.myapplication;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CharacterData {

    ArrayList<Characters> charData = new ArrayList<>();
    ArrayList<TimeRep> timereps = new ArrayList<>();


    public CharacterData() {
        addCharacter();
        addMessages();
    }

    public String charMessage(int id, int random) {
        String res = null;

        if (charData.size() > id) {
            res = charData.get(id).message[random];
        }

        return res;
    }

    public int charVoice(int id, int random) {
        int res = 0;

        if (charData.size() > id) {
            res = charData.get(id).voice_id[random];
        }

        return res;
    }

    public String contentText(int time, int charID) {

        String text = timereps.get(charID).message[time];

        return text;
    }

    public int notifSoundUri(int time, int charID) {
        int uri = timereps.get(charID).sound_uri[time];

        return uri;
    }

    class Characters {
        public final String charName;
        public final int image_id;
        public final String defaultText;
        public final String[] message;
        public final int defaultvoice_id;
        public final int[] voice_id;


        public Characters (String charName, int image_id, String defaultText, String[] message, int defaultvoice_id, int[] voice_id) {
            this.charName = charName;
            this.image_id = image_id;
            this.defaultText = defaultText;
            this.message = message;
            this.defaultvoice_id = defaultvoice_id;
            this.voice_id = voice_id;

        }
    }

    class TimeRep {
        public final String[] message;
        public final int[] sound_uri;

        public TimeRep (String[] message, int[] sound_uri) {
            this.message = message;
            this.sound_uri = sound_uri;
        }
    }


    /*
* 00 熊野
* 01 瑞鳳
* 02 島風
* 03 舞風
* 04 神風
* 05 秋月
* */
    private void  addCharacter() {
        Characters id0 = new Characters("熊野", R.drawable.ic_launcher_foreground, "ごきげんよう、わたくしが重巡、熊野ですわ！", Kumano, R.raw.kumano_introduction, Kumano_Voice);
        Characters id1 = new Characters("瑞鳳", R.drawable.ic_launcher_foreground, "瑞鳳です。軽空母ですが、錬度があがれば、正規空母並の活躍をおみせできます。", Zuiho, R.raw.zuiho_introduction, Zuiho_Voice);
        Characters id2 = new Characters("島風", R.drawable.ic_launcher_foreground, "駆逐艦島風です。スピードなら誰にも負けません。速きこと、島風の如し、です！", Shimakaze, R.raw.shimakaze_introduction, Shimakaze_Voice);
        Characters id3 = new Characters("舞風", R.drawable.ic_launcher_foreground, "こんにちは！陽炎型駆逐艦舞風です。暗い雰囲気は苦手です！", Maikaze, R.raw.maikaze_introduction, Maikaze_Voice);
        Characters id4 = new Characters("神風", R.drawable.ic_launcher_foreground, "待たせたわね、司令官。神風型駆逐艦、一番艦、神風。推参です！\n" + "みんな、いい？ ついてらっしゃい！", Kamikaze, R.raw.kamikaze_introduction, Kamikaze_Voice);
        Characters id5 = new Characters("秋月", R.drawable.ic_launcher_foreground, "秋月型防空駆逐艦、一番艦、秋月。\n" + "ここに推参致しました。お任せください！", Akizuki, R.raw.akizuki_introduction, Akizuki_Voice);

        Collections.addAll(charData, id0 ,id1, id2, id3, id4, id5);


    }

    private void addMessages() {
        TimeRep id0 = new TimeRep(Kumano_timerep, Kumano_timerep_Voice);
        TimeRep id1 = new TimeRep(Zuiho_timerep, Zuiho_timerep_Voice);
        // 島風実装されたらこ↑こ↓
        TimeRep id3 = new TimeRep(Maikaze_timerep, Maikaze_timerep_Voice);
        TimeRep id4 = new TimeRep(Kamikaze_timerep, Kamikaze_timerep_Voice);
        TimeRep id5 = new TimeRep(Akizuki_timerep, Akizuki_timerep_Voice);

        Collections.addAll(timereps, id0, id1, id3, id4, id5);
    }


        String[] Kumano     = {"あら提督、熊野に何かご用？", "今頃ご出勤？\nのろまなのねぇ……", "この熊野に気安く触るなんて、提督も何か勘違いされているのではなくって？", "提督。ま、まぁ…… よ、よくやってるじゃない。\n褒めてあげてもいいのよ。", "ん……んぅぅ……ふぁ……ぁぁぁ……\n" + "わたくし、ちょっと眠くなってきましたわ……"};
        int[]  Kumano_Voice = {R.raw.kumano_tap_1, R.raw.kumano_tap_2, R.raw.kumano_tap_3, R.raw.kumano_tap_4, R.raw.kumano_idle};
        String[] Zuiho      = {"九九艦爆は、脚が可愛いのよ、脚が。", "彗星は彗星で悪くないんだけれど、\n" + "整備大変なのよー、整備が。", "天山はー…って、あれ？んっ。 提督？\n" + "格納庫まさぐるの止めてくれない？んぅっ。っていうか、邪魔っ", "提督？　あの…私、卵焼きいっぱい焼いたんだけど…食べりゅ？　\n" + "わっ、ほんと？　\n" + "えへへ…よかった。", "近海の索敵や、輸送船団の護衛も、大事よねぇ……\n" + "って、提督ぅ、仕事、しようよぉ"};
        int[]   Zuiho_Voice = {R.raw.zuiho_tap_1, R.raw.zuiho_tap_2, R.raw.zuiho_tap_3, R.raw.zuiho_tap_4, R.raw.zuiho_idle};
        String[] Shimakaze  = {"なんですかー提督！？", "ｵｩｯ!?", "駆けっこしたいんですか？負けませんよ？", "えっ？提督、走り疲れたの？おっそーい！\n" + "…でも、頑張ったね！", "んぁ？･･･ん、今、連装砲ちゃんとお話したの。ふぅ･･･\n" + "だって退屈なんだもん！"};
        int[] Shimakaze_Voice = {R.raw.shimakaze_tap_1, R.raw.shimakaze_tap_2, R.raw.shimakaze_tap_3, R.raw.shimakaze_tap_4, R.raw.shimakaze_idle};
        String[] Maikaze    = {"あれー？元気ないぞぅ。", "おはようございます…って今何時？\n" + "まぁいっかぁ！", "おお？提督ノリがいいね、一緒に踊る？", "提督ー、私と一緒に踊ろうよー！", "ねぇ～、踊らないの提督～？"};
        int[] Maikaze_Voice = {R.raw.maikaze_tap_1, R.raw.maikaze_tap_2, R.raw.maikaze_tap_3, R.raw.maikaze_tap_4, R.raw.maikaze_idle};
        String[] Kamikaze   = {"やめて春風、私そういうのあまり好きじゃないの。\n" + "って、司令官じゃない！ どういうことなの？ 説明して頂戴！", "旧型ですって？ 馬鹿ね。駆逐艦の実力は、スペックじゃないのよ？", "司令官、私を呼んだ？ 準備はできてるわ。", "司令官、疲れてるみたい。\n" + "よし、私がなにか温かい飲み物、淹れてあげる。\n" + "ちょっと待ってて。はい、お待ちどうさま。\n" + "どう、温まる？ ホントに？ よかったぁ♪", "第一駆逐艦？ ああ……昔はそんな風にも呼ばれたときもあったけど。\n" + "神風よ、神風！ ネームシップなんだから、しっかり覚えてよね！"};
        int[] Kamikaze_Voice = {R.raw.kamikaze_tap_1, R.raw.kamikaze_tap_2, R.raw.kamikaze_tap_3, R.raw.kamikaze_tap_4, R.raw.kamikaze_idle};
        String[] Akizuki    = {"秋月、推参します！", "この秋月の長10cm砲と高射装置\n" + "この力で、艦隊をきっとお守りします！", "長10cm砲ちゃん、あんまり暴れないでぇ！\n" + "あら、あらら？ 提…督？ ああっ、失礼致しました！", "司令、いつもお疲れ様です。\n" + "この秋月が、艦隊と司令をお護りします。\n" + "だ、大丈夫！", "長10cmよし、高射装置よし、酸素魚雷、よし……よし！\n" + "万全ね、大丈夫！ えっと、あとは……。"};
        int[] Akizuki_Voice = {R.raw.akizuki_tap_1, R.raw.akizuki_tap_2, R.raw.akizuki_tap_3, R.raw.akizuki_tap_4, R.raw.akizuki_idle};

        String[] Kumano_timerep = {
                "深夜0時ですわ。",
                "1時よ。お肌に悪いわ……熊野の美貌に対する挑戦なの？",
                "提督？　いつまで起きていらっしゃるの？　2時よ。",
                "深夜3時ですって！？　ありえませんわ！",
                "深夜4時よ。付き合ってられませんわ。",
                "もぉーっ……！　深夜っていうか……朝5時ですわ！",
                "6時よ。ちゃんと目をお開けなさいな。",
                "7時になりましてよ。提督のおかげで、寝不足でしてよ……？",
                "8時ですわ。私の髪、梳すくってくださる？",
                "もう9時よ。遅刻でなくって？",
                "あら、今日は何もありませんの？　10時ですのに……",
                "11時。仕方ないので、付き合ってあげますわ。",
                "12時。わたくし、ランチにはサンドイッチを所望しますわ。",
                "提督？　わたくし「こんびに」とやらのサンドイッチ、初めていただきましたわ。意外にいけるんですのね。あぁ、13時ですわ。",
                "14時よ。食事の後は仮眠をとりますの。では、ごきげんよう。提督。", "15時ね。仮眠の後はスッキリするので、勉強も仕事も、艦これも。効率が良いと言われているわ。",
                "提督、16時ですわ。熱心に、何を見てらっしゃいますの？",
                "17時。熊野はエステの予約がありますの。そろそろ失礼させてもらっていいかしら？",
                "18時。わたくし、エステ中でしてよ、提督。遠慮してくださるかしら。",
                "19時ね。……はぁ、気持ちよかった。あぁ。提督、いらしたの？",
                "提督、20時よ。ローズヒップティーでも、ご一緒にいかが？",
                "21時になりましたわ。遅めのディナーも、よろしいんじゃなくて？",
                "22時。私、美容のため仮眠いただきますわ。",
                "23時です、提督。夜はこれから、どうされるのかしら。",
        };
        int[] Kumano_timerep_Voice = {
                R.raw.kumano_timerep_00,
                R.raw.kumano_timerep_01,
                R.raw.kumano_timerep_02,
                R.raw.kumano_timerep_03,
                R.raw.kumano_timerep_04,
                R.raw.kumano_timerep_05,
                R.raw.kumano_timerep_06,
                R.raw.kumano_timerep_07,
                R.raw.kumano_timerep_08,
                R.raw.kumano_timerep_09,
                R.raw.kumano_timerep_10,
                R.raw.kumano_timerep_11,
                R.raw.kumano_timerep_12,
                R.raw.kumano_timerep_13,
                R.raw.kumano_timerep_14,
                R.raw.kumano_timerep_15,
                R.raw.kumano_timerep_16,
                R.raw.kumano_timerep_17,
                R.raw.kumano_timerep_18,
                R.raw.kumano_timerep_19,
                R.raw.kumano_timerep_20,
                R.raw.kumano_timerep_21,
                R.raw.kumano_timerep_22,
                R.raw.kumano_timerep_23
        };

        String[] Zuiho_timerep = {
                "日付が変わったよ、提督。",
                "現在時刻、〇一〇〇",
                "時刻は〇二〇〇。眠くない？",
                "提督、〇三〇〇になりました",
                "時刻は…〇四〇〇よ。朝じゃないのぉ",
                "現在時刻…〇五〇〇…。とうとう朝になっちゃった",
                "〇六〇〇。おもいっきり朝よ?",
                "〇七〇〇です。提督、朝ご飯作って?",
                "時刻は、〇八〇〇です。",
                "現在時刻、〇九〇〇よ。一応直掩機飛ばしとく？",
                "一〇〇〇です。さあ、お仕事お仕事",
                "一一〇〇。お弁当食べちゃう？",
                "お昼です！提督、お弁当広げましょ♪私の作った玉子焼き、食べるぅ?",
                "午後の部です！一三〇〇よ",
                "時刻は、一四〇〇になりました",
                "一五〇〇よ。スイーツ食べたいなぁー…ねぇ？提督♪",
                "時刻は、一六、〇〇ですっ♪",
                "一七〇〇。見てみて、夕日！\n" + "･･･ねぇ･･･\n" + "大好き♪",
                "日没です。一八〇〇よ",
                "ヒトマルマ･･･あー、つまり、7時よ。日が暮れたわね",
                "二〇〇〇。お腹空かない？",
                "現在時刻、二一〇〇よ。半舷上陸で呑みに行っちゃう？",
                "提督、二二〇〇です。今日も疲れたねぇ。え？疲れてないのぉ？",
                "現在時刻、二三〇〇。ふぁぁぁ…早く寝て、早く起きようよ…",
        };
        int[] Zuiho_timerep_Voice = {
                R.raw.zuiho_timerep_00,
                R.raw.zuiho_timerep_01,
                R.raw.zuiho_timerep_02,
                R.raw.zuiho_timerep_03,
                R.raw.zuiho_timerep_04,
                R.raw.zuiho_timerep_05,
                R.raw.zuiho_timerep_06,
                R.raw.zuiho_timerep_07,
                R.raw.zuiho_timerep_08,
                R.raw.zuiho_timerep_09,
                R.raw.zuiho_timerep_10,
                R.raw.zuiho_timerep_11,
                R.raw.zuiho_timerep_12,
                R.raw.zuiho_timerep_13,
                R.raw.zuiho_timerep_14,
                R.raw.zuiho_timerep_15,
                R.raw.zuiho_timerep_16,
                R.raw.zuiho_timerep_17,
                R.raw.zuiho_timerep_18,
                R.raw.zuiho_timerep_19,
                R.raw.zuiho_timerep_20,
                R.raw.zuiho_timerep_21,
                R.raw.zuiho_timerep_22,
                R.raw.zuiho_timerep_23
        };

        // 島風実装されたらこ↑こ↓
        String[] Maikaze_timerep = {
                "午前0時、日付が変わりました",
                "午前1時を舞風がお知らせしますね。",
                "午前2時かぁ、そろそろ寝ないと、ねぇ？",
                "午前3時です、お肌にわるいかもって",
                "午前4時。提督、そろそろホント、朝になっちゃうからぁ",
                "夜明けです。午前5時になりました。",
                "朝です。午前6時です。提督、朝ごはんの用意しますね",
                "舞風が、午前7時をお知らせします！",
                "午前8時になりました～。今日も1日、頑張りまっしょーう！",
                "午前9時です。舞風はそろそろ踊りたいなぁ～。",
                "午前10時です。いい風ね～。",
                "午前11時です。そろそろ、小腹がすいてきた…かも～。",
                "午後0時をお知らせします。ランチ何にする？",
                "午後1時です。食べたら動かないと～、ほらワンツー！",
                "午後2時、がんばっていきましょ～。",
                "ポォ～ン！舞風が午後3時をお知らせします！",
                "午後4時です。そろそろ夕方ですねぇ。",
                "午後5時になりました。そろそろ日没です。",
                "午後6時です。晩御飯は何にしよっか？舞風はカツレツとかがいいな～。",
                "午後7時です。提督、ご飯タイムですよ？仕事も艦これもちょっとお休み。",
                "午後8時をお知らせしますね。あぁ～。",
                "舞風が9時をお知らせします。そろそろ夜戦の時間ね。",
                "午後10時です。夜のダンスタイムですね。夜戦にいきます？",
                "午後11時になりました。提督、そろそろ休まなくて大丈夫～？"};
        int[] Maikaze_timerep_Voice = {
                R.raw.maikaze_timerep_00,
                R.raw.maikaze_timerep_01,
                R.raw.maikaze_timerep_02,
                R.raw.maikaze_timerep_03,
                R.raw.maikaze_timerep_04,
                R.raw.maikaze_timerep_05,
                R.raw.maikaze_timerep_06,
                R.raw.maikaze_timerep_07,
                R.raw.maikaze_timerep_08,
                R.raw.maikaze_timerep_09,
                R.raw.maikaze_timerep_10,
                R.raw.maikaze_timerep_11,
                R.raw.maikaze_timerep_12,
                R.raw.maikaze_timerep_13,
                R.raw.maikaze_timerep_14,
                R.raw.maikaze_timerep_15,
                R.raw.maikaze_timerep_16,
                R.raw.maikaze_timerep_17,
                R.raw.maikaze_timerep_18,
                R.raw.maikaze_timerep_19,
                R.raw.maikaze_timerep_20,
                R.raw.maikaze_timerep_21,
                R.raw.maikaze_timerep_22,
                R.raw.maikaze_timerep_23
        };

        String[] Kamikaze_timerep = {
                "よし！今日は私が秘書艦として、司令官のお世話をしっかりしてあげる。いいでしょ？ 丁度、深夜零時。時報も任せておいて。",
                "〇一〇〇。ふわぁ～…少し眠いわね。でも、大丈夫。任せておいて。",
                "〇二〇〇。丑三つ時よ。なに、司令官怖いのー？ 可愛いとこあるのね。",
                "〇三〇〇。えっと…お手洗いとか、司令官、行きたくない？ 私、付きあってあげてもいいけど。…どう、かな？",
                "〇四〇〇。よかった、もうすぐ朝だ。えっ、夜？ 別に全然…怖く、ない…",
                "〇五〇〇。違う！海だったら全然平気なんだから。陸の夜だと勝手が違うの。もぅ！",
                "〇六〇〇。司令官、総員起こし、かけますね。艦隊、総員起こし、朝です！朝が来ました！みんな、起きてー♪",
                "〇七〇〇。よし！司令官、朝食は私が作りますね。待ってて。 はい、お待ちどうさま。麦飯は大盛にしておいたからね。",
                "〇八〇〇。私の朝食、どうだった？ うん、よし！ じゃあ、片付けものしたら、艦隊運用始めましょう。ちょっと待ってて。",
                "〇九〇〇。今朝は何から始める？ 遠征？任務？ いやいや、艦隊出撃かな。 司令官、どうするー？",
                "一〇〇〇。よし！やっぱり遠征かな？船団護衛なら私に任せてよね。…じゃなかった！私が出たら秘書艦できないし！",
                "一一〇〇。もうお昼じゃない。どうする？ また私が作ってもいいけれど…用意しよっか、司令官の昼食。ん？",
                "一二〇〇。はい、用意しました！お昼は塩おにぎりです。 どう？塩は濃いめに握ってあるから。お味噌汁もあります。",
                "一三〇〇。どうだった、お昼？ ちょっと野菜が足りないかな。 貴重だものね、新鮮野菜。",
                "一四〇〇。私の塩おにぎり、美味しかったでしょ？ 伊達に長く…あっ、長くない！ 私、何にも長くないからね！",
                "ヒトゴーマルマ…。あっ、羽黒さん！お疲れ様です。お元気そうで…えっ、あ、はい。私も元気です。 ほらっ、ほらっ！ばっちりです！",
                "一六〇〇。輸送作戦ですか？ そうですね、魚雷がないと不安ではありますが…。ええ、やってみせます。もちろん！",
                "一七〇〇。あっ、司令官。日が落ちますね。綺麗…。 シンガポールで見た夕日…懐かしいです。野風も…元気かな。",
                "一八〇〇。司令官、日が落ちました…ね。艦隊を港に戻しましょう。夕食も私が用意しますね。楽しみに待ってて。",
                "一九〇〇。はい、司令官。神風特製の豚汁です！ 野菜たっぷりです！ 麦飯との相性もバッチリなんです。召し上がれ♪",
                "フタマルマ……。あれ？足柄さん！ くんくん…この匂い、カツカレーですか？ わぁ、大変。司令官、食べれる？ あっ、いくんだ。偉い！",
                "二一〇〇。ふぅ～、美味しかった。足柄さん、勝利のカツカレー、ご馳走さまです！ ふぅ…食べすぎちゃったぁ。",
                "二二〇〇。えっ、司令官…なに？ 怖いものは何って？ うーん…船団護衛で怖いのはやっぱり潜水艦…かな。 えっ、嘘だろって？",
                "二三〇〇。そんなことない、潜水艦は怖いですよ。もちろん敵機も怖いです。 夜？ 海の上での夜は、別に怖くはないです。"
        };
        int[] Kamikaze_timerep_Voice = {
                R.raw.kamikaze_timerep_00,
                R.raw.kamikaze_timerep_01,
                R.raw.kamikaze_timerep_02,
                R.raw.kamikaze_timerep_03,
                R.raw.kamikaze_timerep_04,
                R.raw.kamikaze_timerep_05,
                R.raw.kamikaze_timerep_06,
                R.raw.kamikaze_timerep_07,
                R.raw.kamikaze_timerep_08,
                R.raw.kamikaze_timerep_09,
                R.raw.kamikaze_timerep_10,
                R.raw.kamikaze_timerep_11,
                R.raw.kamikaze_timerep_12,
                R.raw.kamikaze_timerep_13,
                R.raw.kamikaze_timerep_14,
                R.raw.kamikaze_timerep_15,
                R.raw.kamikaze_timerep_16,
                R.raw.kamikaze_timerep_17,
                R.raw.kamikaze_timerep_18,
                R.raw.kamikaze_timerep_19,
                R.raw.kamikaze_timerep_20,
                R.raw.kamikaze_timerep_21,
                R.raw.kamikaze_timerep_22,
                R.raw.kamikaze_timerep_23
        };

        String[] Akizuki_timerep = {
                "司令、この秋月が、時刻を報告します。現在時刻、〇〇〇〇、です！",
                "〇一〇〇をご報告します。こんな形でよろしいでしょうか、司令？",
                "〇二〇〇です。今夜は月が…月が綺麗ですね、司令。ん、司令？",
                "〇三〇〇です、司令。この時間はとても静かですね。本当に静かです…",
                "〇四〇〇です。司令、眠くはありませんか？　濃い目のお茶、入れましょう",
                "〇五〇〇となりました、司令。はい。秋月、総員起こしの準備、了解です！",
                "〇六〇〇です。おはようございます、司令。はい、気持ちのよい朝ですね♪",
                "〇七〇〇です。司令、朝食の用意、こちらです。簡単で…すいません",
                "〇八〇〇。第六一駆逐隊、出撃準備、完了です。いつでも抜錨、出来ます！",
                "〇九〇〇です。護衛する主力艦は戦艦？　空母ですか？　お任せください♪",
                "一〇〇〇をご報告します。司令、演習もしっかりこなしていきたいですね",
                "一一〇〇です。司令、お昼はどうなさいますか？　秋月がご用意しましょうか？",
                "一二〇〇です。司令、握り飯と沢庵のお昼です。あ…簡単で、すいません…",
                "一三〇〇となりました。司令、すいません。夕食は豪華に頑張りますから！",
                "一四〇〇です。初月、元気かなぁ…あ、司令。午後も頑張りましょう！",
                "一五〇〇です。あ、はい。明石さんにもとってもお世話になりました。",
                "一六〇〇です。はい、後部にも高射装置を付けましたから、もう万全です！",
                "一七〇〇をご報告します。日も落ちますから、対空警戒もここまでですね。",
                "一八〇〇となりました。はい、夕食の準備、秋月、豪華に頑張ります！",
                "一九〇〇です！　熱々の麦飯、沢庵、そして牛缶に、お味噌汁も♪　どう？",
                "二〇〇〇。はい、秋月、今日はすこし奮発してしまいました。どうでした？",
                "二一〇〇です。え、涼月ですか？　はい、自慢の妹ですよ。ええ！",
                "二二〇〇。あ、翔鶴さん、瑞鶴さん。お疲れ様です。はい、大丈夫です！",
                "二三〇〇。少し、緊張しました。はぁ…司令、本日も大変お疲れ様でした。"
        };
        int[] Akizuki_timerep_Voice = {
                R.raw.akizuki_timerep_00,
                R.raw.akizuki_timerep_01,
                R.raw.akizuki_timerep_02,
                R.raw.akizuki_timerep_03,
                R.raw.akizuki_timerep_04,
                R.raw.akizuki_timerep_05,
                R.raw.akizuki_timerep_06,
                R.raw.akizuki_timerep_07,
                R.raw.akizuki_timerep_08,
                R.raw.akizuki_timerep_09,
                R.raw.akizuki_timerep_10,
                R.raw.akizuki_timerep_11,
                R.raw.akizuki_timerep_12,
                R.raw.akizuki_timerep_13,
                R.raw.akizuki_timerep_14,
                R.raw.akizuki_timerep_15,
                R.raw.akizuki_timerep_16,
                R.raw.akizuki_timerep_17,
                R.raw.akizuki_timerep_18,
                R.raw.akizuki_timerep_19,
                R.raw.akizuki_timerep_20,
                R.raw.akizuki_timerep_21,
                R.raw.akizuki_timerep_22,
                R.raw.akizuki_timerep_23
        };

}
