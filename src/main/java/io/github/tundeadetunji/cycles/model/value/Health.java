package io.github.tundeadetunji.cycles.model.value;
import io.github.tundeadetunji.cycles.model.domain.Period;

public class Health {
    public String constructDetails(Period period){
        if (period == Period.First) return first;
        if (period == Period.Second) return second;
        if (period == Period.Third) return third;
        if (period == Period.Fourth) return fourth;
        if (period == Period.Fifth) return fifth;
        if (period == Period.Sixth) return sixth;
        if (period == Period.Seventh) return seventh;
        throw new RuntimeException("Invalid period!");
    }
    public String constructDetailsInYoruba(Period period){
        if (period == Period.First) return firstInYoruba;
        if (period == Period.Second) return secondInYoruba;
        if (period == Period.Third) return thirdInYoruba;
        if (period == Period.Fourth) return fourthInYoruba;
        if (period == Period.Fifth) return fifthInYoruba;
        if (period == Period.Sixth) return sixthInYoruba;
        if (period == Period.Seventh) return seventhInYoruba;
        throw new RuntimeException("Invalid period!");
    }
    public String constructDetailsInBulgarian(Period period){
        if (period == Period.First) return firstInBulgarian;
        if (period == Period.Second) return secondInBulgarian;
        if (period == Period.Third) return thirdInBulgarian;
        if (period == Period.Fourth) return fourthInBulgarian;
        if (period == Period.Fifth) return fifthInBulgarian;
        if (period == Period.Sixth) return sixthInBulgarian;
        if (period == Period.Seventh) return seventhInBulgarian;
        throw new RuntimeException("Invalid period!");
    }

    private final String first = "During this period the vitality and constitutional health should be at its best and, if it is below normal, it will be more quickly and easily increased and strengthened by normal living and the avoidance of the violation of any natural laws. Plenty of outdoor walking, good air, drinking plenty of water and eating proper foods, avoiding foods that are overheating, especially the starches and raw or rare meats-this will yield results. The eyes should be guarded against overuse or use in bright electric lights or sunlight, and if any operation is planned, or system of health building is to be adopted, this is the period in which to start these things.";
    private final String firstInYoruba = "Lakoko yii iwulo ati ilera t’olofin yẹ ki o wa ni ti o dara julọ ati pe, ti o ba wa ni isalẹ deede, yoo ni iyara ati irọrun pọ si ati ni okun nipasẹ gbigbe deede ati yago fun irufin eyikeyi awọn ofin adayeba. Pupọ ti nrin ita gbangba, afẹfẹ ti o dara, mimu omi pupọ ati jijẹ awọn ounjẹ to dara, yago fun awọn ounjẹ ti o gbona ju, paapaa awọn sitashi ati awọn ẹran aise tabi awọn ẹran to ṣọwọn - eyi yoo mu awọn abajade jade. Awọn oju yẹ ki o ṣọra fun ilokulo tabi lilo ninu awọn ina ina mọnamọna ti o tan imọlẹ tabi oorun, ati pe ti wọn ba gbero iṣẹ abẹ eyikeyi, tabi eto ile ilera lati gba, asiko yii ni lati bẹrẹ awọn nkan wọnyi.";
    private final String firstInBulgarian = "През този период жизнеността и конституционалното здраве трябва да са в най-добрия си вид и ако е под нормалното, то по-бързо и лесно ще бъде увеличено и укрепено чрез нормален живот и избягване на нарушаването на всякакви природни закони. Много разходки на открито, добър въздух, пиене на много вода и ядене на подходящи храни, избягване на храни, които прегряват, особено нишестето и сурово или рядко месо - това ще даде резултати. Очите трябва да се пазят от прекомерна употреба или използване на ярка електрическа светлина или слънчева светлина и ако се планира някаква операция или трябва да се приеме система за укрепване на здравето, това е периодът, в който да започнете тези неща.";
    private final String second = "This is a period in which many light and temporary physical conditions may affect the body, and passing emotional conditions affect the mind. In other words, during this period a person may have temporary trouble with the stomach, bowels, blood stream, and nerves; these conditions seem to come quickly, last but a few days, and pass away quickly. None of these should be neglected; each should be given immediate attention, but there need be no anxiety regarding the continuance of such conditions if immediate attention is given, for all of the influences tend to bring rapid changes in the health and physical condition of the body during these fifty-two days. During this period there are apt to be days with headaches, upset stomachs, trouble with the eyes or the ears, catarrh, coughs, aches and pains through mild forms of cold, and with women occasionally aches and pains in the breasts and abdomen. During this period everyone should try to be cheerful and not permit the mind to dwell upon the temporary conditions that affect the body, but simply attend promptly to the checking of any condition that may arise and then cast it out of the mind.\n";
    private final String secondInYoruba = "Eyi jẹ akoko kan ninu eyiti ọpọlọpọ ina ati awọn ipo ti ara igba diẹ le ni ipa lori ara, ati gbigbe awọn ipo ẹdun ni ipa lori ọkan. Ni awọn ọrọ miiran, lakoko asiko yii eniyan le ni wahala fun igba diẹ pẹlu ikun, ifun, ṣiṣan ẹjẹ, ati awọn ara; Awọn ipo wọnyi dabi pe o wa ni iyara, ṣiṣe ṣugbọn awọn ọjọ diẹ, ti o si kọja ni iyara. Ko si ọkan ninu awọn wọnyi yẹ ki o wa ni igbagbe; ọkọọkan yẹ ki o fun ni akiyesi lẹsẹkẹsẹ, ṣugbọn ko nilo aibalẹ nipa lilọsiwaju iru awọn ipo bẹ ti a ba fun ni akiyesi lẹsẹkẹsẹ, nitori gbogbo awọn ipa naa maa n mu awọn ayipada iyara wa ni ilera ati ipo ti ara ti ara ni awọn ọjọ mejilelaadọta wọnyi. . Ni asiko yii o yẹ lati jẹ awọn ọjọ pẹlu orififo, ikun inu, wahala pẹlu oju tabi eti, catarrh, Ikọaláìdúró, irora ati irora nipasẹ awọn ọna tutu ti tutu, ati pẹlu awọn obinrin lẹẹkọọkan awọn irora ati irora ninu ọmu ati ikun. Ni asiko yii gbogbo eniyan yẹ ki o gbiyanju lati ni idunnu ati ki o maṣe gba ọkan laaye lati gbe lori awọn ipo igba diẹ ti o kan ara, ṣugbọn nirọrun wa ni kiakia si iṣayẹwo ipo eyikeyi ti o le dide ati lẹhinna sọ ọ jade kuro ninu ọkan.";
    private final String secondInBulgarian = "Това е период, в който много леки и временни физически състояния могат да засегнат тялото, а преминаващите емоционални състояния засягат ума. С други думи, през този период човек може да има временни проблеми със стомаха, червата, кръвообращението и нервите; тези състояния изглежда настъпват бързо, продължават само няколко дни и изчезват бързо. Нито едно от тях не трябва да се пренебрегва; на всеки трябва да се обърне незабавно внимание, но няма нужда да има безпокойство относно продължаването на такива състояния, ако се обърне незабавно внимание, тъй като всички влияния са склонни да водят до бързи промени в здравето и физическото състояние на тялото през тези петдесет и два дни . През този период са склонни да има дни с главоболие, стомашно разстройство, проблеми с очите или ушите, катар, кашлица, болки и болки чрез леки форми на настинка, а при жените от време на време болки и болки в гърдите и корема. През този период всеки трябва да се опита да бъде бодър и да не позволява на ума да се занимава с временните състояния, които засягат тялото, а просто да се занимава незабавно с проверката на всяко състояние, което може да възникне, и след това да го изхвърли от ума.";
    private final String third = "This is a period when accidents may happen, and often sudden operations come into one's life, of either a minor or major nature. Likewise, suffering by fire or injury through sharp instruments, falls, or sudden blows, is more likely during this period than any other. Persons should be careful of their food and not overeat, and the body should be kept normally warm because during this period there will be a tendency toward colds, often resulting from overeating or overheating the body. The blood stream should be kept clean and the bowels active, so that blood conditions will not result in sores, boils, eczema, rash, or other more serious conditions of the skin and blood. The blood pressure also should be watched during this period, for there will be a tendency for it to rise, and overwork or strain should be avoided. Any abnormal strain upon any part of the body is very apt to bring a breaking down during this period.\n";
    private final String thirdInYoruba = "Eyi jẹ akoko ti awọn ijamba le ṣẹlẹ, ati nigbagbogbo awọn iṣẹ airotẹlẹ wa sinu igbesi aye eniyan, ti boya kekere tabi iseda pataki. Bakanna, ijiya nipasẹ ina tabi ipalara nipasẹ awọn ohun elo didasilẹ, isubu, tabi awọn fifun ojiji lojiji, ṣee ṣe ni akoko yii ju eyikeyi miiran lọ. Èèyàn gbọ́dọ̀ ṣọ́ra fún oúnjẹ wọn, kí wọ́n má sì jẹun ju bó ṣe yẹ lọ, ara sì gbọ́dọ̀ jẹ́ kí ara máa móoru nítorí pé lákòókò yìí, ìtẹ̀sí sí òtútù máa ń wáyé, èyí tó máa ń yọrí sí àjẹjù tàbí gbígbóná janjan. Osan ẹjẹ yẹ ki o wa ni mimọ ati awọn ifun ṣiṣẹ, ki awọn ipo ẹjẹ ko ni ja si awọn egbò, õwo, àléfọ, sisu, tabi awọn ipo miiran ti o ṣe pataki julọ ti awọ ara ati ẹjẹ. A tún gbọ́dọ̀ wo ìfúnpá ẹ̀jẹ̀ ní àkókò yìí, nítorí ìtẹ̀sí yóò wà fún gbígbé, àti pé ó yẹ kí a yẹra fún iṣẹ́ àṣejù tàbí ìdààmú. Eyikeyi igara ajeji lori eyikeyi apakan ti ara jẹ pipe pupọ lati fa fifọ ni akoko yii.";
    private final String thirdInBulgarian = "Това е период, в който могат да се случат злополуки и често внезапни операции навлизат в живота на човек, както по-малки, така и по-големи. По същия начин страданието от пожар или нараняване чрез остри инструменти, падания или внезапни удари е по-вероятно през този период от всеки друг. Хората трябва да внимават с храната и да не преяждат, а тялото да се поддържа нормално топло, защото през този период ще има склонност към настинки, често в резултат на преяждане или прегряване на тялото. Кръвният поток трябва да се поддържа чист и червата активни, така че кръвните състояния да не доведат до рани, циреи, екзема, обрив или други по-сериозни заболявания на кожата и кръвта. Кръвното налягане също трябва да се следи през този период, тъй като ще има тенденция да се покачва и трябва да се избягва прекомерна работа или напрежение. Всяко необичайно напрежение върху която и да е част от тялото е много вероятно да доведе до срив през този период.";
    private final String fourth = "During this period the nervous system of your body will be tried to its utmost and there will be many tendencies toward nervousness expressing itself in the functioning of various organs or in an outer form of restlessness and uneasiness. Too much study, reading, planning, or use of the mind and nervous system will surely bring definite reactions during this period. More sleep and more rest are required during this period than in any other part of the year. Fretfulness and nervousness may also affect the digestion, the functioning of the stomach, and may also produce a nervous heart which may cause misgivings and inconvenience. Persons who have been laboring too long or too tediously with mental problems or work requiring mental strain should be forced to relax and rest during this period, or a mental breakdown is inevitable.";
    private final String fourthInYoruba = "Lakoko yii eto aifọkanbalẹ ti ara rẹ yoo gbiyanju si opin rẹ ati pe ọpọlọpọ awọn itara si aifọkanbalẹ yoo wa ni sisọ ararẹ ni iṣẹ ti awọn ẹya ara oriṣiriṣi tabi ni ọna ita ti aini isinmi ati aibalẹ. Pupọ pupọ ikẹkọ, kika, iṣeto, tabi lilo ọkan ati eto aifọkanbalẹ yoo mu awọn aati pato wa ni akoko yii. Oorun diẹ sii ati isinmi diẹ sii ni a nilo lakoko yii ju ni eyikeyi apakan miiran ti ọdun. Fretfulness ati aifọkanbalẹ le tun ni ipa lori tito nkan lẹsẹsẹ, iṣẹ ṣiṣe ti inu, ati pe o tun le ṣe agbejade ọkan aifọkanbalẹ eyiti o le fa aibalẹ ati aibalẹ. Awọn eniyan ti o ti n ṣiṣẹ pipẹ tabi arẹwẹsi pẹlu awọn iṣoro ọpọlọ tabi iṣẹ ti o nilo igara ọpọlọ yẹ ki o fi agbara mu lati sinmi ati sinmi ni asiko yii, tabi fifọ ọpọlọ jẹ eyiti ko ṣeeṣe.";
    private final String fourthInBulgarian = "През този период нервната система на тялото ви ще бъде изпитана до краен предел и ще има много тенденции към нервност, изразяваща се във функционирането на различни органи или във външна форма на безпокойство и безпокойство. Твърде много изучаване, четене, планиране или използване на ума и нервната система със сигурност ще доведе до определени реакции през този период. През този период са необходими повече сън и повече почивка, отколкото през която и да е друга част от годината. Раздразнението и нервността също могат да повлияят на храносмилането, функционирането на стомаха и също могат да причинят нервно сърце, което може да причини опасения и неудобства. Хората, които са работили твърде дълго или твърде досадно с психични проблеми или работа, изискваща умствено напрежение, трябва да бъдат принудени да се отпуснат и да си почиват през този период, или психическият срив е неизбежен.";
    private final String fifth = "This is another good period, when the health should be very good, especially if normal living is indulged in, and the great outdoors utilized for deep breathing, fairly long walks, and good exercise. There will probably be a tendency during this period to overindulge in the things that please the flesh, such as the eating of preferred foods, elaborate meals and banquets, rich concoctions, spicy drinks, and so forth, and even overindulgence morally and ethically in many ways. All of this must be avoided during this period in order to prevent serious conditions. This is a good period in which to recover from fevers, chronic conditions, or other abnormal or subnormal conditions of the body which have been existing for some time. During this period, mental suggestions, metaphysical principles, and right thinking will have more effect upon the body and the health than at any other period.\n";
    private final String fifthInYoruba = "Eyi jẹ akoko ti o dara miiran, nigbati ilera yẹ ki o dara pupọ, paapaa ti igbesi aye deede ba ni itara, ati awọn ita gbangba nla ti a lo fun mimi jinlẹ, awọn irin-ajo gigun, ati adaṣe to dara. Ó ṣeé ṣe kí ìtẹ̀sí wà láàárín sáà yìí láti lọ́wọ́ nínú àwọn ohun tí ń tẹ́ ẹran ara lọ́rùn, irú bí jíjẹ àwọn oúnjẹ tí a yàn láàyò, oúnjẹ gbígbóná janjan àti àsè, àwọn oúnjẹ ọlọ́ràá, ọtí líle, àti bẹ́ẹ̀ bẹ́ẹ̀ lọ, àti ṣíṣe àṣejù ní ti ìwà híhù àti ìwà ọmọlúwàbí nínú ọ̀pọ̀lọpọ̀. awọn ọna. Gbogbo eyi gbọdọ wa ni yee ni akoko yii lati le ṣe idiwọ awọn ipo to ṣe pataki. Eyi jẹ akoko ti o dara ninu eyiti o le gba pada lati inu ibà, awọn ipo onibaje, tabi awọn ipo ajeji miiran tabi ti ara ti o ti wa fun igba diẹ. Ni asiko yii, awọn imọran ọpọlọ, awọn ilana metaphysical, ati ironu to tọ yoo ni ipa diẹ sii lori ara ati ilera ju ni eyikeyi akoko miiran.";
    private final String fifthInBulgarian = "Това е още един добър период, когато здравето трябва да е много добро, особено ако се отдадете на нормален начин на живот и на открито се използва за дълбоко дишане, сравнително дълги разходки и добри упражнения. Вероятно ще има тенденция през този период да се прекалява с нещата, които харесват плътта, като ядене на предпочитани храни, сложни ястия и банкети, богати смеси, пикантни напитки и т.н., и дори морално и етично прекомерно угаждане в много начини. Всичко това трябва да се избягва през този период, за да се предотвратят сериозни състояния. Това е добър период за възстановяване от треска, хронични състояния или други необичайни или субнормални състояния на тялото, които съществуват от известно време. През този период умствените внушения, метафизичните принципи и правилното мислене ще имат по-голям ефект върху тялото и здравето, отколкото във всеки друг период.";
    private final String sixth = "This period is another one in which overindulgence should be carefully avoided in regard to work, mental strain, eating, or any of the pleasures of the flesh. It is a period during which the skin, throat, internal generative system, and kidneys may become affected; therefore, plenty of water should be drunk during this period, the bowels kept open, and rest with outdoor exercise should be indulged in more frequently than mental strain or overwork.";
    private final String sixthInYoruba = "Àkókò yìí tún jẹ́ àkókò mìíràn nínú èyí tí a gbọ́dọ̀ yẹra fún ṣíṣe àṣejù ní ti iṣẹ́, ìdààmú èrò orí, jíjẹ, tàbí èyíkéyìí nínú àwọn adùn ẹran ara. O jẹ akoko ti awọ ara, ọfun, eto ipilẹṣẹ inu, ati awọn kidinrin le ni ipa; nitorina, ọpọlọpọ omi yẹ ki o mu yó ni asiko yii, awọn ifun wa ni ṣiṣi silẹ, ati isinmi pẹlu idaraya ita gbangba yẹ ki o wa ni igba diẹ sii ju igara opolo tabi iṣẹ apọju.";
    private final String sixthInBulgarian = "Този период е друг, в който трябва внимателно да се избягва прекомерното угаждане по отношение на работата, умственото напрежение, храненето или някое от удоволствията на плътта. Това е период, през който кожата, гърлото, вътрешната полова система и бъбреците могат да бъдат засегнати; следователно през този период трябва да се пие много вода, червата да се държат отворени и почивката с упражнения на открито трябва да се отдава по-често, отколкото умственото натоварване или преумора.";
    private final String seventh = "This is the period during which chronic or lingering conditions are often contracted, and which remain a long time and cause considerable trouble in overcoming. Everyone should be especially careful of catching colds or contracting serious contagious fevers during this period by avoiding the places where such things may be contacted. The mind and whole nature is very apt to be despondent and below normal in the ability to ward off and fight an incoming condition, and even the blood stream may be lowered in its vitality at this period, and, therefore, unable to fight even the normal amount of germs or unfavorable influences that generally come in contact with every human being. It is not a good time, however, for taking medicine or having an operation performed, or for starting any new or drastic method of improving the health unless in an emergency or unless it is to be continued over a long period, so that its real effect will come into the next period of fifty-two days, which will be period number one of the next cycle. The eyes, the ears, and in fact any one of the five senses may become affected during this period, and care should be taken that colds or other conditions do not linger during this period or continue without proper expert attention. It is one of the most serious periods of the whole year for each person, in regard to diseases and chronic conditions.";
    private final String seventhInYoruba = "Eyi ni akoko lakoko eyiti awọn ipo onibaje tabi awọn ipo aiduro nigbagbogbo jẹ adehun, ati eyiti o wa ni igba pipẹ ti o fa wahala nla ni bibori. Ó yẹ kí gbogbo ènìyàn ṣọ́ra ní pàtàkì láti kó òtútù tàbí kíkó ibà tó lè ranni lọ́wọ́ ní àkókò yìí nípa yíyẹra fún àwọn ibi tí irú nǹkan bẹ́ẹ̀ ti lè bá. Okan ati gbogbo iseda jẹ itẹlọrun pupọ lati ni ibanujẹ ati ni isalẹ deede ni agbara lati yago fun ati ja ipo ti nwọle, ati paapaa ṣiṣan ẹjẹ le dinku ni agbara rẹ ni akoko yii, ati, nitorinaa, ko lagbara lati ja paapaa awọn iye deede ti awọn germs tabi awọn ipa ti ko dara ti o wa ni olubasọrọ pẹlu gbogbo eniyan. Kii ṣe akoko ti o dara, sibẹsibẹ, fun lilo oogun tabi ṣiṣe iṣẹ abẹ, tabi bẹrẹ eyikeyi ọna tuntun tabi ọna ti o lagbara lati ṣe ilọsiwaju ilera ayafi ti o ba wa ni pajawiri tabi ayafi ti o yẹ ki o tẹsiwaju fun igba pipẹ, nitorinaa o jẹ gidi. ipa yoo wa sinu akoko atẹle ti ọjọ mejilelaadọta, eyiti yoo jẹ nọmba akoko ọkan ninu ọmọ atẹle. Awọn oju, awọn etí, ati ni otitọ eyikeyi ọkan ninu awọn imọ-ara marun le ni ipa ni akoko yii, ati pe o yẹ ki a ṣe akiyesi pe otutu tabi awọn ipo miiran ko duro ni akoko yii tabi tẹsiwaju laisi akiyesi ọlọgbọn to dara. O jẹ ọkan ninu awọn akoko to ṣe pataki julọ ni gbogbo ọdun fun eniyan kọọkan, ni iyi si awọn arun ati awọn ipo onibaje.";
    private final String seventhInBulgarian = "Това е периодът, през който често се получават хронични или продължителни състояния, които остават дълго време и причиняват значителни проблеми за преодоляване. Всеки трябва да бъде особено внимателен за настинка или заразяване със сериозни заразни трески през този период, като избягва местата, където могат да се докоснат такива неща. Умът и цялата природа са много склонни да бъдат отчаяни и под нормалното в способността си да отблъскват и да се борят с настъпващо състояние, и дори кръвният поток може да бъде намален в своята жизненост в този период и, следователно, неспособен да се бори дори с нормално количество микроби или неблагоприятни влияния, които обикновено влизат в контакт с всяко човешко същество. Не е подходящ момент обаче за приемане на лекарства или извършване на операция, или за започване на нов или драстичен метод за подобряване на здравето, освен ако не е спешно или ако не трябва да продължи за дълъг период от време, така че да е реално ефектът ще дойде в следващия период от петдесет и два дни, който ще бъде период номер едно от следващия цикъл. Очите, ушите и всъщност всяко едно от петте сетива могат да бъдат засегнати през този период и трябва да се внимава настинките или други състояния да не се задържат през този период или да продължат без подходящо експертно внимание. Това е един от най-сериозните периоди в годината за всеки човек по отношение на заболявания и хронични състояния.";
}