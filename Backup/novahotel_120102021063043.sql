-- MariaDB dump 10.17  Distrib 10.4.13-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: novahotel_1
-- ------------------------------------------------------
-- Server version	10.4.13-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbl_acompanhantes`
--

DROP TABLE IF EXISTS `tbl_acompanhantes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_acompanhantes` (
  `acompanhantes_id` int(11) NOT NULL,
  `acompanhantes_nome` varchar(200) NOT NULL,
  `acompanhantes_cpf` varchar(20) NOT NULL,
  `acompanhantes_hospedagem_id` int(11) NOT NULL,
  KEY `acompanhantes_hospedagem_id` (`acompanhantes_hospedagem_id`),
  CONSTRAINT `acompanhantes_hospedagem_id` FOREIGN KEY (`acompanhantes_hospedagem_id`) REFERENCES `tbl_hospedagem` (`hospedagem_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_acompanhantes`
--

LOCK TABLES `tbl_acompanhantes` WRITE;
/*!40000 ALTER TABLE `tbl_acompanhantes` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_acompanhantes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_cabecalho`
--

DROP TABLE IF EXISTS `tbl_cabecalho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_cabecalho` (
  `cabecalho_id` int(11) NOT NULL AUTO_INCREMENT,
  `cabecalho_logo` longblob DEFAULT NULL,
  `cabecalho_linha_1` varchar(500) DEFAULT NULL,
  `cabecalho_linha_2` varchar(500) DEFAULT NULL,
  `cabecalho_linha_3` varchar(500) DEFAULT NULL,
  `cabecalho_linha_4` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`cabecalho_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_cabecalho`
--

LOCK TABLES `tbl_cabecalho` WRITE;
/*!40000 ALTER TABLE `tbl_cabecalho` DISABLE KEYS */;
INSERT INTO `tbl_cabecalho` VALUES (1,'ÿØÿà\0JFIF\0\0\0\0\0\0ÿÛ\0C\0		\n\r\Z\Z $.\' \",#(7),01444\'9=82<.342ÿÛ\0C			\r\r2!!22222222222222222222222222222222222222222222222222ÿÀ\0\0æ\0æ\"\0ÿÄ\0\0\0\0\0\0\0\0\0\0\0	\nÿÄ\0µ\0\0\0}\0!1AQa\"q2‘¡#B±ÁRÑğ$3br‚	\n\Z%&\'()*456789:CDEFGHIJSTUVWXYZcdefghijstuvwxyzƒ„…†‡ˆ‰Š’“”•–—˜™š¢£¤¥¦§¨©ª²³´µ¶·¸¹ºÂÃÄÅÆÇÈÉÊÒÓÔÕÖ×ØÙÚáâãäåæçèéêñòóôõö÷øùúÿÄ\0\0\0\0\0\0\0\0	\nÿÄ\0µ\0\0w\0!1AQaq\"2B‘¡±Á	#3RğbrÑ\n$4á%ñ\Z&\'()*56789:CDEFGHIJSTUVWXYZcdefghijstuvwxyz‚ƒ„…†‡ˆ‰Š’“”•–—˜™š¢£¤¥¦§¨©ª²³´µ¶·¸¹ºÂÃÄÅÆÇÈÉÊÒÓÔÕÖ×ØÙÚâãäåæçèéêòóôõö÷øùúÿÚ\0\0\0?\0ñz(¢¨È(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(¢Š\0(®£LÑtGĞ#ÔµK«ˆÊcÌ|Œó6“ØÓşÃà¯ú^ßÿ\0Ö^Õ^Öeû7kÜå(®³ì>ÿ\0 µçıòøİağWı¯?ï“ÿ\0Æèö¾OîgæNŠë>Ãà¯ú^ß\'ÿ\0Ò}‡Áô¼ÿ\0¾Oÿ\0£Úù?¸=Ÿš9J+wV‡ÃZ¦]]\\Ü3cÂ¨õ9Qšµ¡xN-DÚ¶¥©Ç§‹¥y B…İã@K9ç¸õÁâ›¨”nôfÛ²9Š+»Ó´}2çÄ÷\Zş‚–‚5Ú×+væHÉÀVäímÌÈ}áÅcøÂ‡Bn­ï£¾´óŞÚI6æRr¥I>‡Ûé•\ZÑr°İ)%sœ¢º;o\nMeŞ^Ş[Üc˜ÈÏ¨Â*ÏØ|ÿ\0AkÏûàÿ\0ñºn§“û…ìüÑÉÑ]gØ|ÿ\0A[Ïûäÿ\0ñº_°ø/ş‚×Ÿ÷Éÿ\0ãt½¯“û‡ìüÑÉQ]gØ|ÿ\0A[Ïûäÿ\0ñº_°ø/ş‚·Ÿ÷Éÿ\0ãt{O\'÷³óG%Et>%Ñ¬4»}>{	f’;¥gİ)0¤c½\\õ\\d¤®ˆ”\\]˜QEB\n(¢€\n(¢€\n(¢€\n(¢€\n+Ô~\\?ë—E7ù8“nq»N3øU›oŠ—‘™-|)<Ñƒ´´w†}8ÜW,«ÏÆ1½¼ÍÕò§\'¿‘È	cƒÀÚt²Â\'5\0ÏvTÙéLÿ\0„—Ãÿ\0ô\'Úÿ\0àT•ÜÂÅÕèM»ÿ\0¿Íÿ\0Æèÿ\0…‹ªÿ\0Ğ›uÿ\0ÿ\0¬oQïÄÖĞé/Àáÿ\0á$ğÿ\0ı	ö¿ø%Cuâ\rkI¢‡Â¶ĞÊèU%.J88ïŠï[â>¦ªY¼t\0$Ìxÿ\0ÈuÄøÃÆÒx®+XVËìq@ÌÌ‚mûØà÷GAŸÌÖ”Ôœµ—©qKI~)R‹yš0†CêáNÑø×SáSÃV:d±ëIpìøu6ûİ”ã$ÜcœŒdúúoİ_^Zê#RĞ|a§¶œy[K™DksÊô\0?Îµ•V¥k\Zi«ÜËğ&›İ¬“Çcm{2_AÂO“e»dP{ç¿`\rk<6—K¥ø§Mœ®Ÿ¥fÒæ<o&2@\0c’èØ<àn=HĞÒ.4Ûë›gÃ‰z¼`^[A®\"0Bà±“ƒ•<¦´ìx~ò4Ğ•Ô4æ_–İHËƒ©$\\s·§,¦Ü›ÛúÛüˆÅ(¤9![\ZÍ¯êWòhÚ’FmîÙŠ€w£D»G9S\Zç8“Ú¡·³m\"Xü?ªÉÚ†¡u=å¼ò.è–R…\"b!‰bN9ÇËZĞ\\hé¤ÀóÇøRõ—ÈI\"9´›\'pn>é`y\'‚HåNj SôZ‰¦[ï>ãÌÒn’2Ys“¸‘Œ.p6œÿ\0\rG3{ÿ\0Vşµ*Çã$êv‰$C|-#ûjDª“’Bñ’»IÇ­`<RD@’7Lò7)¯P¾Õ­d¼—KÖ&:F½n‰\ZjË\nŸ<c®q”\'¡ä}Ú©o¬>“e5·‰õ]7]Ó›\"Šaq31î§Œ\0	9b:q]0«%­ıyJšr½Ï:†EŠxähÖEVQº0¡®—şMş…_ü\nz­â­GHÔ.á:MªDˆH ‚~PT’9ù¸Îzq[Şø‹6¤[é\'Iûk£–m¤‚r6œœŸåZOšQRQ×µìL-Y¿ÔÊÿ\0„“@ÿ\0¡B×ÿ\0ªêZÖ‘ya$¾‚ÎfÆÙÒáØ®\'ƒê8ükĞ¿ábjŸô\']ßãÿ\0Æèÿ\0…‰ªĞuÿ\0ÿ\0®tæù?F¢ôæü\'Åòğïı{ì‘×)^Ãÿ\0Uÿ\0¡:ëşÿ\0ş7Kmñ\"âMZÊÂçÃ’ZµÔÉ´—70\\à Î3U\n•ar~(R§NNüßã´W{ñhcÅ6¸ÿ\0Ÿ5ÿ\0ĞŞ¸*é¥>x)w9çI8…QZQE\0QE\0QE\0zWÃ¿ùüKÿ\0\\Ûÿ\0EµpÈÓş¾fÿ\0Ğb¯@øwÿ\0\"‰ë›è¶¯?ùZ×ÄßúUÉKø³õ_‘Ñ?áÇÓõ*`QŠ(®³œ1F(¢€\n(«ÇGÔVYå²š{³ˆ\'|¨¤ú;aĞç†u[+=4DÚ€Ó¯a¿éfhİ–D\nT§Ê	Î¸<ã½ui%ÅŸˆ´4ÒE¥êi-äÑ²ŒJ®Åİr	T\n£Óñ5Ä^xZM3I’÷Q¿°GeÍ¬÷Q\\¼çxSş­ÕÇ˜w3^	Ôğ®µ¬XiWi%‹iÚS+yÛä$yPœÜà9ã€‰Ç~j´[»‰½:¶Ñ›º -ã=zÆw2i (6Å•rê#\0&}İïQÁªµ¶â=óµ†§>/,á^7@ï‚ğÀ|K¡Xë3kÚTzªêîco+ ‡{uÜÀ–eÏ;xäEsZ®³}¬Î$¼”¥ŠÆŠ±Ë£Œ“É=IëP¨¹=vĞ·UEh\\ñ6§o¨İÙ­´ÒO¥¢[äM†R¤üØÉõÇ<ñX”Q]QŠŠ²9¥&İÂ—&’Š¡h¢Š\0+¤Ñä\'áoúı_ı\Z+›®—Eÿ\0Ÿ…¿ëñôjÖu~ë±t÷6>-ÿ\0ÈÓkÿ\0^Kÿ\0¡½pUŞü[ÿ\0‘¦×ş¼—ÿ\0Czàª0ÿ\0Â¡U¾6QEndQE\0QE\0QE\0z_Ã¿ùüKÿ\0\\Ûÿ\0Eµyûÿ\0ÈÓş¾&ÿ\0Ğb¯@øwÿ\0\"‰ë›è¶¯83³YÅm´mFp{üÁGşÊ+–—ñgê¿#¢ÃÏó#¢Š+¨ç\n(­™-Ñ<mw·š÷óCtÊÄ€\"hCƒ\'Î#×kuÛÀÏ[XO­ÜK}s½¥…Íİ­¤Ñ³Çs<Q—Tp1òğX‚pvã½6-;Yñ¥İÆ¢ÒÍwªİİ*§š¡n0ÇÍbv“ãıjØuX«RCGQ]±şÑĞlßÄ~ šYuÛèLzd7’H×1)ùMÑ9Êí\0¬yä±ÜSÄÚ£R•t¹lîtÛP-í-Z™øÖ5rÃ{¨^š_YÜØC¤xV§€	\'‰Fd’òuF+°1?*˜£”lrMeøNÆßRñ†‹evÛ\\^Ãªä€ê\\¼sÈãñí]v™$¶÷^!Õu9m´~úuÜòH‰&ŸÎæâT‰Îòà.Ğƒm|¼	C9ÿ\0x2ûUñi×ğ½ŒV`O¨‹†K\0nf\nüçoCŒËœš¯®ØÏuª\\Ka¦´„ÿ\0CU’(ñr¾dlÊåU[çÎXFY°wWEeäZø\\Óí¯lï6ö[ï®`q§Ï,’DÈÄ92\0J vL¨RÍÈ½¥jÖ1ø¯F·“Sµ@±·´7yHg‰d‘6å‚y’î·`\n\0àcÅqAáıËÃ°Âç‰oä+Ì“Œ‡çYP“²0ÃG>AŞ\rqu¥¬ŞOsöËû§»Ô®	–yÌâ@A\0\"ôà€BF\n®©›LL(¢Š\0(¢Š\0+¥Ñä\'áoúı_ı\ZµÍVç‡îZ]@„¨\rìX=Îd¢¢÷_õĞºĞüZÿ\0‘¢×ş¼—ÿ\0Czà«¾ø·ÿ\0#E¯ıy¯ş†õÀÖxáGĞªÿ\0ÄaEVæAEPEPEP¥|;ÿ\0‘?Ä¿õÍ¿ô[Wá¿K®\\rÑÙÆq$£©?İÿ\0Ê»‡ò\'ø—ş¹·ş‹ját=nçD½BwÄx–pCï\\pæçŸ.ú~GL¹ycÍ°íoE¸Ñ/L3\rÑ6LRöp?‘õ™VõFçU½{«§İ#p\0è£°°ÿ\0=j¥uFö×sV¾ZzF½¨h†e´’3ÀQqm<K,39ÑSƒĞã#±™EPÊßâ-ŞæK¤xÃúeã¾õ¼¶²-,DõØdf\nÈÀÁ=+šæ÷\\Õ|ëË³=åÔŠ­=Ì dœ¹˜àÇ$€\0ìR­-2Xm­uƒ<)r ò`÷o0ír£c)Â1R7¤‘Š\0Ù·ğ}°ø€şÔufµ0†Úò;q2Ê_\"T?Õ”ç\'†xÉ1Øi~Mì—÷Q.£†8şÊ†Y‹nr†PTª©È†â °Îjşµwg}¡èZ„z”?ÚöĞK‹xàd,lŞT…Â…bbu$^¹;]â­^Ã^Ö¡’É–Ö’·WM\"’‘İLÎÃ\n_` qó}Ö+×&µáßèÚ¡§I¯_µÕŒ¢7LP²bEWØDÇ¥˜\nÜddT÷¾m?Æzn‰=ó?R™aµÔ¢€:»°»ğ\nÈv²îÊã8<eŞ)ƒDÖ<E¬jĞx¢Ä¥ÕÉ’Ş!os»ç‘Aß˜€PªY—\0Öâ­+OñíÒê7r^øzmQud·Ş‡’„ep	G‘¸ªÒ(æäÒ4Hô+}Hêº†./%¶XÆœ,f2ÌO×dª@şğ+œ|Ç_Tğ0x¢ãÃ\ZN§=ö³H†[1ròÄóc³s€s6i×^Óm[[²‚î+éæ’	#Ÿr¤¢+S)˜à1ŒŸ–º}cÅZMÅ+é×Ğê(w=½¼qJ‡•°y…Õp%¾RÙÀg Åi\ZÚ½£qìp+‘#€ÎS?Â|¸æ|÷òñÔŠÉ®§IÔ-¼=¨øfö×S²šH®~ÓtcŠh^ÅE$›	tØ¿Â¤ ‚ØºÕ¾Ÿk¬İÃ¥^‹Í<H~Ï6?.íÊ§pŒg8â˜ŠQE\0t~ğ´šÎë‹†hm@aÕÛÛØw?‡ÑÖ:]Æ‘ãm*Úqÿ\0/°”aÑ×Ì5GBñ\rÖ‡+y¼şô,p	ìG¡ÿ\0>•>‘{q¨xÏJ¹¹¼{\'°Ş8ÕŒ”ıëíchòY[s ø·ÿ\0#E¯ıy¯ş†õÀ×}ñoşF‹_úó_ı\rë¥‡ş}[øŒ(¢ŠÜÈ(¢Š\0(¢Š\0(¢Š\0ô¿‡ò\'ø—ş¹·ş‹jós­¬wìƒ×*ŸıW¤|;ÿ\0‘?ÄŸõÍ¿ô[WŸÉÿ\0 ;Oúù›ÿ\0AŠ¹i?ŞÏÕ~GDÿ\0‡ë©RŠ(®£œ±ccq©]¥­ª•ÃÂ€f,Ä\0I$\0&¬ÙèZ† 7YÂ&V‘¢ˆ‡\nfp»ŠÆ‚çàrÊ1–Poøywè¾)UGi?³¾‚êÜ·AŸ`\rlé°G¬ÛøX}ŸvŸ§C$Z¥â;¡´<¯¹v©T`èqó7Ëóã`\0æ!ğşµq¦JP’À+9ºKg1\\î;ÀÆNxÁª³Ù\\[ÚÛ]I ¹b‘X0;NàğÃŒ©ÁÁSŒOo µÎ½‰áBò÷MÒ¦Ó.^7[Ğ¶îRIåK1´22p~@Ù‹!·­ÄÅMËkı˜9TÇçdôä˜1şé÷ ,g\'†õé4ã¨&‰©5ˆŒËö•µsÁÉmØÆ5U´Ûô°[ö²¹m·&òÎI\rŒuVU>•ÙXi×wPÛèzÁ–Òîÿ\0NûUü×ŠĞCf‘<Lj§ho-·†q÷ƒèZxWÆ	á{ımìQã¼ÒB=ÔïŠ;âG]¨	c!\"‚TmÆy-¹Øâ!ğŞ­qc5Ü6dp[ıªTYÈ°ä0Çûy8ÆzsNÃ:¼¶2İÇh!¶û\\‘¬¨eHr?xbÎğ¸e9Æ6İk®×¡ÑõGRÖ˜lô1£Å•Ò4EÊCÈÁ$«¸#Ê*:*³pTµK«Ùé\Z•æ³¬]ZÍe£6…é—(_d—K!\rÊ»G@ŒN\n–,y­]‡J¾îâÔ[´s[79vœåÈó¼ã,@ê@«ñÁÛb·ğ¼Ú•ıäêÊQ´åIWlÇ²I?)ÉH\0A5Ùjö×0êtì{‰5mZìKd¦%’İn™İ¢Üàá¨Rİ˜¬p²xsYQm=téç¹XÒ]–Ëç‚·k™§rà‚AÈ¬ø`šæâ;x\"yg‘Â$h¥™˜œ\0\0ä’{W¤§ƒ®¼A}=‡³ÙÅ\rŒú®ŸŠ’CsäHJó1ÀS½NI)¿[f*eÔtoŠróo‰ï¯#5†?2+).B–”‚på¤É²KaFô;qESVç‡í?èS66K{ÜxXuÓhßòğ¿ı~¯ş_ºÿ\0®…ÓZ›ÿ\0ähµÿ\0¯5ÿ\0ĞŞ¸\Zï¾-ÿ\0ÈÑkÿ\0^kÿ\0¡½p5øQô*·ÆÂŠ(­Ì‚Š( Š( Š( Køwÿ\0\"‰?ë›è¶¯?“ş@vŸõñ7şƒzÃÏùüIÿ\0\\Ûÿ\0EµpÈÓş¾&ÿ\0Ğb®JÅ—ªü‰ÿ\0>Ÿ©NŠ(ÅuœäÏ5´†H%xœ«!dbÖXqØ‚AÁ\"‰æk‹‰&päbì#EEœğª\0Ø\0GŠ\\\Z\0µ§.õÒXÌÛäµY˜DíÇ%3‚~Uç‡¥E=Ô÷\"4…–ÄQ/@Š	8 ä’}I$òI¨°hÁ k©jMm8_İmæÔLŞQo]™ÆxëŠ·¥xcXÖ¶µ”¯`¾i@s¾İñWô\rN»ÓEåÿ\0Úåioc²†VUmÌ3¸–Šì\0¹¶ñ\'†,tØÌö–©=¹hş\\H¥£–B=¸o}ŞõÏV¯.‘ÜÚ¯«9y¼!§›×Ó,5å»ÕT[­³f\0’¡òFx=x÷Íßi×šeÇ‘{m-¼¸ÎÙT©#ÔzŠôm-ñ^Hã•W<ê™#¥¿ï¼c\'‘Š¯šş\"Ò<íHı–ÚãUê Ö$!]¥mÁGÔŠÎ5œ_¼ôÓñ-ÒRZny½±â\r&\r2æÙ­Vµ¼¶[˜|àª±#\r3ÁäVAW\\Z’º9Ú³³­éú¦¡¤Ü4úmıÕ”Ì»\ZKiš6+pJq8öª¡I£iô¦\"kËÛ­Béî¯nf¹¸“åšBîØ\'“À ¥Ú}(ÚŞ”\0•Óèÿ\0òğ¿ı~/şÌí>•ÓèßòğÇı~/ş_‡úì]=Ío‹ò4Zÿ\0×šÿ\0èo\\\rwßÿ\0ähµÿ\0¯5ÿ\0ĞŞ¸\ZŒ?ğ£èUoâ0¢Š+s ¢Š(\0¢Š(\0¢Š(Ô>Û½ç†õëhÊ‡˜yj[ %üêµ·ÃZAäE{¤÷Ä‰æ`ûÑŸAùWe«êZj:XßÜÛ+œ°†R¹>ø«_ğ”øƒşƒz‡ş?ø×#£UNRƒV}Î…VŸ*ROC¶ÿ\0…â¿ùúĞÿ\0ğøÕ/ü+ÿ\0ÏÖ‡ÿ\0€ëÿ\0Æ«9uzëÁÖfÔ¯\ZöâøB¬\'!›;€\\“ëŠ_ìo‰_ó×Qÿ\0Àõÿ\0âë;Õë$-‰²û|=ñS©Su¢`Œ@£ÿ\0iW!â?\nßø^[t¾x\\(Ğ±#Œdr<Îºìo‰_ó×Rÿ\0Àõÿ\0âê†­áÏOdój±]Ïon­)2İ,›\07Ş•¥9ÉKY+y8Å­\"ÆøoÂ¶Zæ÷ê‚Ğ¡>c¶Í‘½–\rÎN1ÇZÑ½±Ñ¡ÕN¦xJöúhÇiäFcÓvÊ}ÇÚ¸*ÖƒÄºåµ˜´ƒU»áUe#hô¨Â´•)·tÿ\0OÈˆÎ)ZÇ¢éZE¿‡$–ÂÂFŸZºe\"\0ë0°H…É80ç˜­˜iW	á\r&›™ï5ö¢¼0dNq…8Ïû»zò1ş)„i%ô—pÇpXŒ¥¦räØ‘ƒõñZqÍo¢ÙÙøWO’Wm\\É#İDÄ˜¢rDl;cj‚ÃĞPG,âÔšzÿ\0[üˆ´âšĞÔ·Ñí.4åÒíıiåCÌ\n/˜#u@H{Œmò*ùç^Ô£×5%›LÓ´É+d™‚› r\n·ñ> íê	ªàZê~!Mh`ÑtÕR¤Øîá€\r‡y|c½\r	v¾&¸³Õå·uKšx£Ó³ş²DO2 >ñ‚?ÙsQfµÕÿ\0VU×B=KBŠéßÄ^ æH<´û6•kG>U|}Ğ9ôü	ÛYö±ñ43´ZhVÑ®ñ%ÃºT‡À#äŒsY0¹¿ƒ\\°¿’Y-õIl£–q•hŸæëòü qïïX·Úæ«©Â!½Ônn\"!$•Ï®=}ë¦æâšğ>FœTÑ{ÄÚzÌQÅ;È²nÀ•UX€~ø\nÇälğN	Áâ¯xwÀúÆ¹dš•œ–‘F$*Ÿhİóc´‚3Çàk–†)\'š8bBòHÁQTrI8\0Wikáßˆ6ëoj·°Â™Ûwªª2rpú“ZÔr„æWó&)JW³·‘­ÿ\0ŠçëDÿ\0¿ÿ\0Æ¨ÿ\0„Å?óõ¢ß…ÿ\0ãUŸıñ#şzê?ø¿ü]UÔ`ñî“c%íõŞ¡\r¼xÜÿ\0m ÔŠæN£vRF¶‚û,Úÿ\0„Å?óõ¢ß…ÿ\0ãU-Ÿ€<BºÎ›yyw¦´V“¤›bxà<V?‰uí^ÛEĞ¤‡S¼åƒ22ÌÀ¹Úœyê:æá*ñıµüoñ§Öœou÷\nR¥k3§ø·ÿ\0#E¯ıy¯ş†õÀÕ›İBóR™e½ºšæE]¡årÄ¸Éúš­]T áĞç©.i9 ¢Š+BŠ( Š( Š( Š( ÛNÒ.uŸÛ[Ú¼i\"]4›¤$\rÃ°>µ_ş]oşm¿ïëÿ\0ñ5ÈÑXû9&ìÿ\0_h¬®¿şmoşm¿ïëÿ\0ñ4ëD`ŞZ{_ÿ\0‰®BŠ|’ïø=¿¬ÿ\0„Uÿ\0Ÿ‹/ûí¿øš_ø@5_ùø³ÿ\0¾ßÿ\0‰®JŠv©üß€sC±»«xcQÑ­…ÄÆ)\"\'k4,Nß®@ëZZ‰tØ¥±}jŞåå²‰àŠ{b2Ñ0+±#¦ã‚{W!EÑ´„§ÊïÒldÇ‹®µı[TÒ\r” ;ù7A™‚hÂ(ù²\Z4à\r`ø—ÄÖZ·Ø´‹Y ·kÇ¾–IˆŞò¶zÀ\0:úzsÊQS\Z	Jì§Uµdu0ø+Y½‰n¤–yFâ³»oüx<ÔƒÀ:¯üüYÿ\0ßmÿ\0Ä×%EU§ßğ4;pğª\"æÌÈ!ßÿ\0‰§ÿ\0Â­Ïí·ıış&¸ê(åŸÀ9£Ûñ;øAõ¯ùü¶ÿ\0¿¯ÿ\0ÄÒk,0×–¤zÿ\0‰®>Š\\’ïø<{~\'cã[w´ÒôKi\n—Š&F+Ğ¨8ü«¢Š¨G–6&ræw\n(¢¬¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(\0¢Š(ÿÙ','HOTEL AVENIDA - (65) 3266-1624','','','');
/*!40000 ALTER TABLE `tbl_cabecalho` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_cliente`
--

DROP TABLE IF EXISTS `tbl_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_cliente` (
  `cliente_id` int(11) NOT NULL AUTO_INCREMENT,
  `cliente_nome` varchar(200) NOT NULL,
  `cliente_RG` varchar(20) DEFAULT NULL,
  `cliente_CPF` varchar(20) DEFAULT NULL,
  `cliente_endereco_logradouro` varchar(200) NOT NULL,
  `cliente_endereco_numero` varchar(200) NOT NULL,
  `cliente_endereco_bairro` varchar(200) NOT NULL,
  `cliente_endereco_cidade` varchar(200) NOT NULL,
  `cliente_endereco_estado` varchar(2) NOT NULL,
  `cliente_telefone` varchar(20) NOT NULL,
  `cliente_endereco_complemento` varchar(200) DEFAULT NULL,
  `cliente_endereco_cep` varchar(100) NOT NULL,
  PRIMARY KEY (`cliente_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_cliente`
--

LOCK TABLES `tbl_cliente` WRITE;
/*!40000 ALTER TABLE `tbl_cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_hospedagem`
--

DROP TABLE IF EXISTS `tbl_hospedagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_hospedagem` (
  `hospedagem_id` int(11) NOT NULL AUTO_INCREMENT,
  `hospedagem_cliente_id` int(11) NOT NULL,
  `hospedagem_cliente_nome` varchar(200) NOT NULL,
  `hospedagem_quarto_id` int(11) NOT NULL,
  `hospedagem_quarto_numero` varchar(100) NOT NULL,
  `hospedagem_usuario_id` int(11) NOT NULL,
  `hospedagem_usuario_nome` varchar(200) NOT NULL,
  `hospedagem_status` varchar(100) NOT NULL,
  `hospedagem_checkin_data` date DEFAULT NULL,
  `hospedagem_checkin_hora` time DEFAULT NULL,
  `hospedagem_checkout_data` date DEFAULT NULL,
  `hospedagem_checkout_hora` time DEFAULT NULL,
  `hospedagem_valor_total` double DEFAULT NULL,
  `hospedagem_valor_diaria` double NOT NULL,
  `hospedagem_forma_pagamento` varchar(100) DEFAULT NULL,
  `hospedagem_veiculo_placa` varchar(100) DEFAULT NULL,
  `hospedagem_veiculo_marca` varchar(100) DEFAULT NULL,
  `hospedagem_total_diarias` double DEFAULT NULL,
  `hospedagem_quantidade_diarias` double DEFAULT NULL,
  `hospedagem_valor_desconto` double DEFAULT NULL,
  PRIMARY KEY (`hospedagem_id`),
  KEY `hospedagem_cliente_id` (`hospedagem_cliente_id`),
  KEY `hospedagem_quarto_id` (`hospedagem_quarto_id`),
  KEY `hospedagem_usuario_id` (`hospedagem_usuario_id`),
  CONSTRAINT `hospedagem_cliente_id` FOREIGN KEY (`hospedagem_cliente_id`) REFERENCES `tbl_cliente` (`cliente_id`),
  CONSTRAINT `hospedagem_quarto_id` FOREIGN KEY (`hospedagem_quarto_id`) REFERENCES `tbl_quarto` (`quarto_id`),
  CONSTRAINT `hospedagem_usuario_id` FOREIGN KEY (`hospedagem_usuario_id`) REFERENCES `tbl_usuario` (`usuario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_hospedagem`
--

LOCK TABLES `tbl_hospedagem` WRITE;
/*!40000 ALTER TABLE `tbl_hospedagem` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_hospedagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_produto`
--

DROP TABLE IF EXISTS `tbl_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_produto` (
  `produto_id` int(11) NOT NULL AUTO_INCREMENT,
  `produto_nome` varchar(200) NOT NULL,
  `produto_valor` double NOT NULL,
  `produto_quantidade` int(100) NOT NULL,
  PRIMARY KEY (`produto_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_produto`
--

LOCK TABLES `tbl_produto` WRITE;
/*!40000 ALTER TABLE `tbl_produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_produtos_hospedagem`
--

DROP TABLE IF EXISTS `tbl_produtos_hospedagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_produtos_hospedagem` (
  `produtos_hospedagem_id` int(11) NOT NULL,
  `produtos_hospedagem_produtos_id` int(11) NOT NULL,
  `produtos_hospedagem_nome` varchar(100) NOT NULL,
  `produtos_hospedagem_produtos_valor` double NOT NULL,
  `produtos_hospedagem_quantidade` int(11) NOT NULL,
  `produtos_hospedagem_hospedagem_id` int(11) NOT NULL,
  KEY `produtos_hospedagem_produtos_id` (`produtos_hospedagem_produtos_id`),
  KEY `produtos_hospedagem_hospedagem_id` (`produtos_hospedagem_hospedagem_id`),
  CONSTRAINT `produtos_hospedagem_hospedagem_id` FOREIGN KEY (`produtos_hospedagem_hospedagem_id`) REFERENCES `tbl_hospedagem` (`hospedagem_id`),
  CONSTRAINT `produtos_hospedagem_produtos_id` FOREIGN KEY (`produtos_hospedagem_produtos_id`) REFERENCES `tbl_produto` (`produto_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_produtos_hospedagem`
--

LOCK TABLES `tbl_produtos_hospedagem` WRITE;
/*!40000 ALTER TABLE `tbl_produtos_hospedagem` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_produtos_hospedagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_quarto`
--

DROP TABLE IF EXISTS `tbl_quarto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_quarto` (
  `quarto_id` int(11) NOT NULL AUTO_INCREMENT,
  `quarto_numero` varchar(10) NOT NULL,
  `quarto_andar` varchar(10) NOT NULL,
  `quarto_descricao` varchar(200) DEFAULT NULL,
  `quarto_tipo` varchar(100) NOT NULL,
  `quarto_itens_dispo` varchar(200) DEFAULT NULL,
  `quarto_status` varchar(100) NOT NULL,
  PRIMARY KEY (`quarto_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_quarto`
--

LOCK TABLES `tbl_quarto` WRITE;
/*!40000 ALTER TABLE `tbl_quarto` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_quarto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_quarto_acompanhantes`
--

DROP TABLE IF EXISTS `tbl_quarto_acompanhantes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_quarto_acompanhantes` (
  `quarto_acompanhantes_id` int(11) NOT NULL,
  `quarto_acompanhantes_quarto_id` int(11) NOT NULL,
  `quarto_cama_quantidade_acompanhantes` int(11) NOT NULL,
  `quarto_acompanhantes_valor` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_quarto_acompanhantes`
--

LOCK TABLES `tbl_quarto_acompanhantes` WRITE;
/*!40000 ALTER TABLE `tbl_quarto_acompanhantes` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_quarto_acompanhantes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_usuario`
--

DROP TABLE IF EXISTS `tbl_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_usuario` (
  `usuario_id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario_nome` varchar(500) NOT NULL,
  `usuario_login` varchar(100) NOT NULL,
  `usuario_senha` varchar(100) DEFAULT NULL,
  `usuario_tipo` varchar(100) NOT NULL,
  `usuario_inativo` tinyint(1) NOT NULL,
  PRIMARY KEY (`usuario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_usuario`
--

LOCK TABLES `tbl_usuario` WRITE;
/*!40000 ALTER TABLE `tbl_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-20 18:30:44
