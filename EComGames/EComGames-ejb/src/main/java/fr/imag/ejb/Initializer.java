/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.ejb;

import fr.imag.entities.Achat;
import fr.imag.entities.Categorie;
import fr.imag.entities.Cle;
import fr.imag.entities.Editeur;
import fr.imag.entities.Jeu;
import fr.imag.entities.Plateforme;
import fr.imag.entities.PrixJeu;
import fr.imag.entities.Utilisateur;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;

/**
 * EJB Singleton qui remplit la BD au démarrage. A enelever en prod
 *
 * @author aaitmouloud
 */
@Singleton
@LocalBean
@Startup
public class Initializer {

    final static private Logger LOGGER = Logger.getLogger(Initializer.class);

    @PersistenceContext(unitName = "EComGamesPU")
    private EntityManager em;

    @PostConstruct
    void init() {
        LOGGER.info("Initialisation des données.");
        Editeur editeur = new Editeur("Sega Games", LOGGER.getAllAppenders() + " C'est plus fort que toi", null);

        Jeu jeu = new Jeu("Legend of Zelda", "Link sauve Zelda", 1990, 3, "ftp://");
        Cle cle = new Cle(jeu);
        Cle cle2 = new Cle(jeu);
        Cle cle3 = new Cle(jeu);
        PrixJeu prixJeu = new PrixJeu(jeu, Calendar.getInstance(), null, 25D);
        PrixJeu prixJeu2 = new PrixJeu(jeu, Calendar.getInstance(), Calendar.getInstance(), 100D);
        Plateforme p = new Plateforme("Playstation 3");
        Plateforme p2 = new Plateforme("PC");
        jeu.setEditeur(editeur);
        jeu.addPlateforme(p);
        em.persist(jeu);

        Utilisateur user = new Utilisateur("toto", "toto", Calendar.getInstance(), "bla@bla.bel");
        em.persist(user);

        user.setEmail("blabla@car.com");
        em.merge(user);

        Achat a = new Achat(user, cle, Calendar.getInstance(), new Short("5"), "Ce jeu est trop bien");
        em.persist(a);

        Jeu jeu2 = new Jeu("Mario Bros", "Mario sauve Peach", 1980, 3, "ftp://");
        jeu2.setEditeur(new Editeur("Nintendo", "Forever and ever after", null));
        jeu2.addCategorie(new Categorie("Plateforme"));
        jeu2.addPlateforme(p);
        jeu2.addPlateforme(p2);
        PrixJeu prixJeu3 = new PrixJeu(jeu2, Calendar.getInstance(), null, 45D);
        Cle cle4 = new Cle(jeu2);
        Cle cle5 = new Cle(jeu2);
        em.persist(jeu2);

        Editeur e1 = new Editeur("Valve");
        Editeur e2 = new Editeur("Blizzard");
        Editeur e3 = new Editeur("EA");
        Editeur e4 = new Editeur("Activision");

        Jeu j1 = new Jeu("GTA San Andreas", "Grand Theft Auto: San Andreas (abrégé GTA:SA ou GTA SA) est un jeu vidéo de tir à la troisième personne, de conduite et daction-aventure, développé par Rockstar North en Écosse (Royaume-Uni). Le jeu vidéo est initialement commercialisé en octobre 2004 sur console PlayStation 21. Il est, par la suite, commercialisé en juin 2005 sur console Xbox4 et sur Microsoft Windows (PC)2. GTA: SA est également commercialisé sur Mac courant lannée 2010. Puis, sur iOS, Android et Windows Phone en décembre 2013 et enfin sur Xbox 360 (remastérisé) le 26 octobre 2014 pour fêter ses 10 ans. Il est le huitième opus en date de la série Grand Theft Auto et le dernier de la trilogie Grand Theft Auto III. Le jeu vidéo se déroule dans lÉtat fictionnel de San Andreas, localisé comme étant un archipel de trois îles fictives, imitant trois grandes villes métropolitaines : Los Santos (localisée à Los Angeles), San Fierro (localisée à San Francisco) et Las Venturas (localisée à Las Vegas), incluant des déserts, lacs, forêts et une montagne qui sépare les villes. Lhistoire se passe en 1992, San Andreas suit les aventures dun membre de gang, Carl « CJ » Johnson, de retour dans sa ville natale, Los Santos (État de San Andreas), après avoir appris le meurtre de sa mère. Johnson retrouve ses vieux amis et ses relations familiales en péril. Tout au long du jeu, Carl se démène pour trouver le meurtrier de sa mère et gagner en influence, en tentant de redonner à son gang sa gloire dantan.", 2005, 18, "http://urlj1");
        j1.setEditeur(e4);
        Jeu j2 = new Jeu("Worms", "Le but étant de détruire tous ses ennemis, on y trouve des armes destructrices à souhait, plus ou moins conventionnelles, allant du bazooka, jusquau fusil en passant par le coup de poing de feu ou le mouton explosif. Ces armes enlèvent de la vie au(x) ver(s) touché(s) ; réduire les points de vie des vers ennemis étant le principal moyen de gagner. Cependant, on peut aussi envoyer les lombrics dans leau où ils se noient aussitôt. Certaines armes sont dailleurs prévues pour ça, comme la très utilisée batte de baseball. Sortir un ver des limites du jeu permet également de léliminer. Un inventaire doutils permet également de creuser le décor, de sagripper au mur ou encore de poser des mines. Un mode \"histoire\" a vu le jour par la suite, et enfin la 3D a fait son apparition dans les derniers opus. La violence (quoique relative) est au rendez-vous mais lambiance créée permet laccès au jeu aux plus jeunes. Grâce aux nombreuses ventes (plus dun million dexemplaires du premier opus de par le monde), léditeur a pu faire une grande quantité de suites.", 2008, 5, "http://urlj2");
        j2.setEditeur(e2);
        Jeu j3 = new Jeu("Pong", "Pong est un jeu vidéo inspiré du tennis de table développé par Ralph Baer et son équipe à Sanders Associates (en) en 1967. Après y avoir joué lors dune première démonstration en mai 1972, Nolan Bushnell, créateur de la société Atari, en fait une version améliorée : Pong. Cest le premier jeu vidéo à connaître un succès populaire. Lidée du jeu est basée sur un jeu inclus dans la console Odyssey, de Magnavox, ce qui entraîna dailleurs une poursuite de cette société contre Atari. Mis sur le marché fin 1972 en tant que borne darcade, Pong est proposé sur une console de salon (la Ping-O-Tronic) à partir de 1975. Il est dabord commercialisé par Sears, puis par Atari un an après.", 1960, 5, "http://urlj3");
        j3.setEditeur(e3);
        Jeu j4 = new Jeu("Flyn", "Flyn, stylisé FLYN, est un jeu vidéo de plates-formes et de réflexion sorti en novembre 2012 développé par Ankama Play pour PC. Un compte Steam est nécessaire pour pouvoir jouer à Flyn. Le joueur incarne un bourgeon issu dun Arbre Monde. Le jeu a reçu une distinction honorable dans la catégorie « Art » lors de lIndependent Games Festival 20131.", 2012, 5, "http://urlj4");
        j4.setEditeur(e1);
        Jeu j5 = new Jeu("Half-Life", "Half-Life est un jeu de tir à la première personne, cest-à-dire un jeu daction à la première personne, développé par Valve Corporation et publié par Sierra le 19 novembre 1998 (via le logiciel Steam depuis 2003). Conçu pour les ordinateurs fonctionnant sous Microsoft Windows et depuis janvier 2013 pour Mac et Linux (sur Steam), le jeu utilise une version profondément modifiée du Quake engine, appelée GoldSrc. Le jeu a été porté sur PlayStation 2 et publié le 15 novembre 2001. Dans ce premier épisode de la saga Half-Life, le joueur incarne le docteur Gordon Freeman, un théoricien récemment diplômé qui, après des expériences de téléportation qui ont mal tourné, doit se battre contre les créatures extraterrestres accidentellement apparues dans le complexe puis contre un commando de militaires envoyés par le gouvernement pour nettoyer la zone. À la sortie du jeu, la critique a salué sa présentation générale et ses nombreuses séquences scénarisées2, et lui a remis une cinquantaine de prix le consacrant jeu de lannée au cours des années 1998 et 19993. Half-Life a grandement influencé le monde du jeu de tir à la première personne. Depuis, ce jeu est considéré comme lun des plus grands4. Avec plus de huit millions dexemplaires vendus depuis, Half-Life est un véritable best-seller du jeu de tir subjectif sur PC5. La franchise Half-Life a connu plus de 15 millions de ventes6.", 2001, 5, "http://urlj5");
        j5.setEditeur(e2);
        Jeu j6 = new Jeu("Gran turismo", "Gran Turismo est un jeu vidéo de course automobile développé par Polyphony Digital et édité par Sony Computer Entertainment en 1997 sur PlayStation. Premier épisode de la série Gran Turismo, le titre a connu un succès international avec près de onze millions dexemplaires écoulés2, ce qui en fait le jeu le plus vendu sur PlayStation3. Le jeu de Kazunori Yamauchi a révolutionné le genre en imposant de nouveaux standards en matière de rendu visuel, de physique et de contenu4.", 1997, 5, "http://urlj6");
        j6.setEditeur(e3);
        Jeu j7 = new Jeu("Plantes VS Zombies", "Plantes contre zombies (Plants vs. Zombies) est un jeu vidéo de tower defense développé et édité par PopCap Games, sorti en 2009 sur PC (Windows, Mac OS), Xbox 360 (XLA), PlayStation 3, PlayStation Vita (PlayStation Network), Android, Bada, iOS, WP7 et Nintendo DS. Le but du jeu est de défendre sa maison contre une invasion de zombies. Avant de rentrer dans la maison et de manger le cerveau du joueur, les zombies doivent passer par le jardin ou par la cheminée et c’est à l’aide de toute une panoplie de plantes et de champignons que le joueur pourra mettre en place sa défense contre des vagues de zombies.", 2013, 5, "http://urlj7");
        j7.setEditeur(e4);
        Jeu j8 = new Jeu("Portal", "Portal est un jeu vidéo de réflexion et daction en vue à la première personne développé par Valve Corporation. Le jeu est disponible pour la première fois le 10 octobre 20071 dans le pack The Orange Box pour Windows et Xbox 360, puis pour la PlayStation 3 le 11 décembre 20072,3,4. La version du jeu pour Windows est également disponible via la plate-forme de téléchargement Steam (appartenant à Valve), et est distribué en version boîte depuis le 9 avril 20085,6. La version Mac est sortie le 12 mai 2010 en même temps que la version Mac de la plate-forme Steam7. La version Linux est disponible depuis le 3 mai 2013. Le jeu consiste en une succession de parcours que le joueur doit franchir en téléportant son personnage ou des objets à laide dune arme capable de créer un portail spatial entre deux surfaces planes, solides et non métalliques. Le personnage est mis au défi par une intelligence artificielle qui lui promet régulièrement de recevoir un gâteau à lissue des tests. Portal est souvent présenté comme lun des jeux les plus originaux de 2007, bien quil soit relativement rapide à terminer. Le jeu est acclamé pour son gameplay unique et lhumour noir omniprésent dans son intrigue. La popularité du jeu pousse Valve à en développer la franchise, notamment en éditant divers produits dérivés tirés des éléments du jeu", 2007, 5, "http://urlj8");
        j8.setEditeur(e1);
        Jeu j9 = new Jeu("Limbo", "Limbo (en anglais : « limbes » ou « oubli ») est un jeu vidéo de plates-formes et de réflexion. Cest le premier titre développé par le studio danois Playdead. Il est sorti le 21 juillet 2010 sur Xbox Live Arcade, le 20 juillet 2011 sur PlayStation 3 à travers la plateforme de téléchargement PlayStation Store1, le 2 août 2011 sur PC via Steam et le 21 décembre 2011 sur Mac via App Store2. Le jeu a également été porte sur Linux3 en juillet 2014. Le jeu est en 2D à défilement horizontal et tient compte de la physique qui gouverne les objets environnants ainsi que le personnage principal. Le joueur guide un jeune garçon muet et sans nom dans des environnements dangereux et doit éviter les nombreux pièges dans lombre. Limbo utilise des graphismes uniquement en noir et blanc, il a recours à un effet de grain et des sons minimalistes et ambiants, qui créent une atmosphère angoissante, souvent associée au genre de lhorreur.", 2005, 5, "http://urlj9");
        j9.setEditeur(e2);
        Jeu j10 = new Jeu("World of goo", "World of Goo est un jeu vidéo de réflexion pour Windows, Mac OS X, Linux, WiiWare, iOS et Android, sorti originellement en octobre 2008. Réalisé par 2D Boy, un petit studio de développement indépendant fondé par Kyle Gabler et Ron Carmel, deux anciens employés dElectronic Arts, le jeu consiste à construire des structures avec de petites créatures rondes — les Goos1 — afin de les amener à un tuyau de sortie. Plusieurs types de Goos différents permettent denrichir la jouabilité au fur et à mesure de la progression. Le jeu a reçu un très bon accueil de la presse et des joueurs, ainsi que plusieurs récompenses.", 2008, 5, "http://urlj10");
        j10.setEditeur(e3);
        Jeu j11 = new Jeu("VVVVVV", "VVVVVV est un jeu vidéo de plates-formes/puzzle en 2D créé par Terry Cavanagh. Il est développé avec Adobe Flash et sort le 11 janvier 2010 sur Microsoft Windows et Mac OS X. Il est ensuite réécrit en C++ par Simon Roth en 2011 et est proposé dans la 3e édition du Humble Indie Bundle, une opération proposant à la vente une série de jeux vidéo indépendants à prix libre. Le portage en C++ permet la sortie, auparavant annulée, de VVVVVV sur Linux. Une version sort également sur le eShop de la Nintendo 3DS et un portage en binaire4 pour Open Pandora est en développement. Celui-ci aura besoin des fichiers des versions Microsoft Windows, Mac OS X ou Linux pour fonctionner. Le jeu propose dincarner Viridian, capitaine dun engin spatial, perdu dans une autre dimension à la recherche des membres de son équipe. Le joueur ne peut pas sauter, mais à la place inverser le sens de la gravité, ce qui permet au capitaine Viridian de marcher tantôt au sol, tantôt au plafond. Le style graphique est très influencé par celui du Commodore 64 ; de la même manière également, les musiques du jeu fonctionnent sur le principe du chiptune, cest-à-dire quelle sont réalisées directement par le hardware de la machine. Magnus Pålsson, qui les a composées, a sorti la bande originale du jeu sous le nom de PPPPPP.", 2012, 5, "http://urlj11");
        j11.setEditeur(e4);
        Jeu j12 = new Jeu("Minecraft", "Minecraft est un jeu vidéo indépendant de type « bac à sable » (construction complètement libre - sandbox en anglais) développé par le Suédois Markus Persson alias Notch, puis par le studio de développement Mojang. Ce jeu vidéo plonge le joueur dans un univers réaliste mais cubique : tout est composé de blocs en 3D pixelisés. La MineCon, un congrès en lhonneur de Minecraft, a célébré la sortie officielle du jeu le 19 novembre 20111. Minecraft est à lorigine développé pour être un jeu sur navigateur Web2, puis sur Windows, Mac et Linux (à laide de Java3). Un portage sur téléphone mobile existe également, Minecraft Pocket Édition, sorti sur les smartphones Android et sur les terminaux iOS4. Une version pour Xbox 360 est sortie le 11 mai 2012, développée par 4J Studios5. Lactuelle version de Minecraft sur Xbox 360 est la version 1.6.2. Une version Playstation 3 développée par Mojang est disponible depuis le 18 décembre 20136. La version PS4 est sortie le 4 septembre 2014 sur le PlayStation Store ; la version Xbox One a été publiée le lendemain7. À ses débuts, le jeu sappelait Cave Game, puis le nom définitif de Minecraft a été inventé lors dun échange sur IRC entre le développeur et des joueurs : après avoir été nommé Minecraft: Order of the Stone8 (en référence à un webcomic du nom de Order of the Stick)9, le nom fut finalement raccourci en Minecraft pour plus de simplicité. Le jeu vidéo est également décliné sous plusieurs formes dans lunivers des jeux physiques (réels) : papercraft (origami), produits dérivés (figurines, vêtements, peluches...) et boîtes de jeu Lego.", 2007, 5, "http://urlj12");
        j12.setEditeur(e1);
        Jeu j13 = new Jeu("Skyrim", "The Elder Scrolls V: Skyrim, ou Skyrim, est un jeu vidéo de rôle et daction, développé par Bethesda Game Studios et édité par Bethesda Softworks, sorti le 11 novembre 2011. Cest le cinquième opus de la série de jeux The Elder Scrolls, il est précédé par Arena, Daggerfall, Morrowind et Oblivion. Le jeu met le joueur dans la peau dun nouveau venu dans la contrée de Bordeciel, alors déchirée par une guerre civile quune invasion de dragons belliqueux ne fait quempirer. Le personnage interprété savère en fait être le dernier « enfant de dragon », seule personne capable de se mesurer aux conflits qui ravagent le pays et de rétablir la paix. À cette trame principale sajoutent de nombreuses quêtes annexes qui invitent le joueur choisir entre plusieurs partis et à découvrir un monde ouvert inspiré de la culture nordique, mêlant vastes plaines, sommets enneigés et vallées boisées. Très grand succès critique et commercial, le jeu cumule en janvier 2014, un peu plus de deux ans après sa sortie, plus de 20 millions dunités écoulées.", 2003, 5, "http://urlj13");
        j13.setEditeur(e2);
        Jeu j14 = new Jeu("Wow", "World of Warcraft (abrégé WoW) est un jeu vidéo de type MMORPG (jeu de rôle en ligne massivement multijoueur) développé par la société Blizzard Entertainment. Cest le 4e jeu de lunivers médiéval-fantastique Warcraft, introduit par Warcraft: Orcs and Humans en 1994. World of Warcraft prend place en Azeroth, près de quatre ans après les événements de la fin du jeu précédent, Warcraft III: The Frozen Throne1 Blizzard Entertainment annonce World of Warcraft le 2 septembre 20012. Le jeu est sorti en Amérique du Nord le 23 novembre 2004, pour les 10 ans de la franchise Warcraft. La première extension du jeu, The Burning Crusade, est sortie en janvier 20073. La deuxième extension, Wrath of the Lich King, est sortie en novembre 20084. La troisième, Cataclysm, est sortie en décembre 2010. La quatrième extension, Mists of Pandaria, est sortie en septembre 20125. La cinquième extension, Warlords of Draenor, est sortie le 13 novembre 20146. Une sixième extension, Legion, est annoncée. Depuis sa sortie, World of Warcraft est le plus populaire des MMORPG. Le jeu tient le Guinness World Record pour la plus grande popularité pour un MMORPG7,8,9,10. En avril 2008, World of Warcraft a été estimé comme rassemblant 62 % des joueurs de MMORPG11. Le 7 octobre 2010, Blizzard annonce que plus de 12 millions de joueurs ont un compte World of Warcraft actif12. Cest à partir de fin 2012 que World of Warcraft commencera à perdre continuellement un nombre croissant de joueurs. Au dernier trimestre 2012, Blizzard annonce le nombre de 9,6 millions d’abonnés à travers le monde, puis 7,7 millions pour le 2e trimestre 2013. World of Warcraft a fêté son 10e anniversaire en novembre 2014. Le mois suivant, à la suite de la sortie de lextension Warlords of Draenor, Blizzard annonce que World of Warcraft repasse le cap des 10 millions dabonnés.", 2010, 5, "http://urlj14");
        j14.setEditeur(e3);
        Jeu j15 = new Jeu("warcraft", "Warcraft III: Reign of Chaos est un jeu vidéo de stratégie en temps réel (STR) développé par Blizzard Entertainment. Il sagit du troisième volet de la série Warcraft et du quatrième jeu de stratégie en temps réel développé par le studio après Warcraft: Orcs and Humans, Warcraft II: Tides of Darkness et StarCraft ayant connu de solides succès critiques et commerciaux. Les versions PC et Macintosh sont publiées en Amérique du Nord par Blizzard Entertainment le 3 juillet 2002, et en Europe par Sierra Entertainment le 5 juillet 2002. Le jeu se déroule dans le monde médiéval-fantastique dAzeroth plusieurs années après les événements de Warcraft II: Beyond the Dark Portal. En plus des humains et des orcs déjà présents dans les deux premiers opus de la série, Warcraft III permet au joueur de commander deux nouvelles factions, les elfes de la nuit et les morts-vivants. Le jeu se distingue de son prédécesseur par ses graphismes entièrement en trois dimensions mais également par lintroduction dun système de héros inspiré des jeux de rôle. À sa sortie, le jeu est très bien accueilli par la presse spécialisée, et il connaît rapidement un important succès commercial avec plus d’un million dexemplaires vendus un mois après parution et 3 millions dexemplaires vendus en moins dun an. Le jeu est récompensé à de nombreuses reprises l’année de sa sortie avec notamment six titres de meilleur jeu PC de l’année et de nombreux titres de meilleur jeu de stratégie de l’année décernés par des sites et des magazines spécialisés. Warcraft III bénéficie dune extension, intitulée Warcraft III: The Frozen Throne, publiée par Blizzard Entertainment le 1er juillet 2003. Comme le jeu original, celle-ci est bien accueillie par les critiques et rencontre rapidement un certain succès commercial avec plus d’un million dexemplaires vendus en moins de deux mois. Aucune suite na été annoncée à ce jour mais en 2004, Blizzard a publié un jeu de rôle en ligne massivement multijoueur intitulé World of Warcraft basé sur l’univers du jeu. Avec plus de onze millions dabonnés en 2011, celui-ci reste lun des jeux les plus célèbres dans le monde.", 2008, 5, "http://urlj15");
        j15.setEditeur(e4);
        Jeu j16 = new Jeu("Mario Bros", "Super Mario Bros est un jeu vidéo de plates-formes développé par Nintendo sorti en 1985 sur Nintendo Entertainment System. Il sagit du premier jeu de la série Super Mario. Le joueur y contrôle Mario et voyage à travers le Royaume Champignon afin de sauver la princesse Peach des griffes de Bowser, lantagoniste de Mario. Le jeu est jouable à deux joueurs, le premier contrôlant Mario et le second Luigi, le frère de ce dernier. Super Mario Bros. était le jeu le plus vendu de tous les temps avec plus de 40 millions de copies vendues dans le monde, jusquà ce quil soit battu par Wii Sports en 2009. En tant que jeu de lancement, Super Mario Bros. a été en partie à lorigine du succès de la Nintendo Entertainment System ainsi que de la fin du krach du jeu vidéo de 1983 aux États-Unis. Étant lun des plus grands succès réalisés par Shigeru Miyamoto et Takashi Tezuka, le jeu donnera lieu par la suite à de nombreuses suites et dérivés. À lui tout seul, Super Mario Bros. imposa un level design à tous les jeux qui lui succèderont. Ainsi, il popularise définitivement le défilement horizontal, les boss et sous-boss de fin de niveau, les raccourcis secrets, le fait de pouvoir recommencer le jeu avec une difficulté accrue. La musique du jeu, composée par Kōji Kondō, est reconnue mondialement ; elle est depuis devenue représentative des jeux vidéo en général.", 1980, 5, "http://urlj16");
        j16.setEditeur(e4);
        Jeu j17 = new Jeu("Legend of Zelda", "The Legend of Zelda, sous-titré The Hyrule Fantasy (« La fantaisie dHyrule »), est un jeu vidéo daction-aventure édité et développé par Nintendo. Il est sorti en 1986 au Japon, sur Family Computer Disk System, et en 1987 aux États-Unis et en Europe, sur Nintendo Entertainment System. Il sest vendu à 6,5 millions dexemplaires3. Ce jeu créé par Shigeru Miyamoto est le premier opus de la série éponyme.", 1990, 5, "http://urlj17");
        j17.setEditeur(e1);

        j1.setImage("resources/images/gta.jpg");
        j2.setImage("resources/images/worms.jpg");
        j3.setImage("resources/images/pong.jpg");
        j4.setImage("resources/images/flyn.jpg");
        j5.setImage("resources/images/half-life.png");
        j6.setImage("resources/images/gt.jpg");
        j7.setImage("resources/images/pvsz.png");
        j8.setImage("resources/images/portal.jpg");
        j9.setImage("resources/images/limbo.png");
        j10.setImage("resources/images/goo.jpg");
        j11.setImage("resources/images/vvvvvv.jpg");
        j12.setImage("resources/images/minecraft.jpg");
        j13.setImage("resources/images/skyrim.jpg");
        j14.setImage("resources/images/wow.jpg");
        j15.setImage("resources/images/warcraft.jpg");
        j16.setImage("resources/images/mario.jpg");
        j17.setImage("resources/images/zelda.jpg");

        j1.addCurrentPrix(15);
        j2.addCurrentPrix(0);
        j3.addCurrentPrix(45);
        j4.addCurrentPrix(25);
        j5.addCurrentPrix(60);
        j6.addCurrentPrix(60);
        j7.addCurrentPrix(15);
        j8.addCurrentPrix(45);
        j9.addCurrentPrix(60);
        j10.addCurrentPrix(45);
        j11.addCurrentPrix(45);
        j12.addCurrentPrix(25);
        j13.addCurrentPrix(15);
        j14.addCurrentPrix(60);
        j15.addCurrentPrix(60);
        j16.addCurrentPrix(25);
        j17.addCurrentPrix(45);

        Categorie c1 = new Categorie("STR");
        Categorie c2 = new Categorie("FPS");
        Categorie c3 = new Categorie("RPG");
        Categorie c4 = new Categorie("Action");
        Categorie c5 = new Categorie("Aventure");
        Categorie c6 = new Categorie("MMO");
        Categorie c7 = new Categorie("Course automobile");
        Categorie c8 = new Categorie("Sport");
        Categorie c9 = new Categorie("Combat");
        Categorie c10 = new Categorie("Moba");
        Categorie c11 = new Categorie("Simulation");

        j1.addCategorie(c1);
        j1.addCategorie(c5);
        j2.addCategorie(c2);
        j3.addCategorie(c3);
        j4.addCategorie(c4);
        j5.addCategorie(c5);
        j6.addCategorie(c6);
        j7.addCategorie(c7);
        j8.addCategorie(c8);
        j8.addCategorie(c9);
        j9.addCategorie(c10);
        j10.addCategorie(c11);
        j11.addCategorie(c1);
        j12.addCategorie(c2);
        j13.addCategorie(c3);
        j14.addCategorie(c4);
        j15.addCategorie(c5);
        j16.addCategorie(c6);
        j17.addCategorie(c7);
        j17.addCategorie(c8);

        em.persist(j1);
        em.persist(j2);
        em.persist(j3);
        em.persist(j4);
        em.persist(j5);
        em.persist(j6);
        em.persist(j7);
        em.persist(j8);
        em.persist(j9);
        em.persist(j10);
        em.persist(j11);
        em.persist(j12);
        em.persist(j13);
        em.persist(j14);
        em.persist(j15);
        em.persist(j16);
        em.persist(j17);

        LOGGER.info("Fin de l'initialisation des données.");
    }
}
