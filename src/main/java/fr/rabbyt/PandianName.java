package fr.rabbyt;

import java.util.Random;

/**
 * Classe qui permet de générer un nom aléatoirement pour un {@link Pandian}.
 * 
 * @author Kilian POUSSE
 * @version 1.0
 * @since 2025-02-01
 */
public class PandianName {

    /**
     * Liste des noms possibles pour nommer un Pandien.
     */
    private static final String[] NAMES = {
        "BooBam", "Pandaios", "Kumai", "Xianbo", "Bambuz", "Pangara", "Changbo", "Tigu", "Valkor", "Blaze",
        "Gizzie", "Zephyr", "Vulca", "Sable", "Panpan", "Omber", "Kairo", "Dapan", "Quillon", "Ashira", "Aka",
        "Missy", "Xylo", "Kaelen", "Grimmar", "Thalor", "Rivael", "Jorlan", "Liora", "Jaxir", "Nyra", "Draka",
        "Oryn", "Skara", "Viron", "Arden", "Juno", "Iskaro", "Charly", "Bob", "Erik", "Susa", "Jean", "Luka", 
        "Bill", "Rock", "Yak", "Dispy", "Lesh", "Byker", "Gepeha", "Nouya", "Kuya", "Pagala", "Taham", "Rua",
        "Nalu", "Wapcla", "Perri", "Cubte", "Konao", "Tyomar", "Gratfono", "Tozrap", "Nekohike", "Tyuni", "Tyspla",
        "Koge", "Dry", "Nyu", "Setai", "Chadgiel", "Lemeunier", "Depres", "Renault", "Remars", "Dugue", "Jacoboni",
        "Py", "Jacob", "PiauToffolon", "Mariotte", "Lehuen", "Cablet", "Camelin", "Makoto", "Aigis", "Ren", "Sumire",
        "Yu", "Rise", "Korumaru", "Teddie", "Morgana", "Thanatos", "Athena", "Thor", "Pharos", "Vi", "Jinx", "Cait",
        "Lucy", "Kevin", "Octavia", "Romain", "Cube", "Sheep", "Brim", "Phoenix", "Sage", "Sova", "Viper", "Cypher",
        "Kill", "Breach", "Omen", "Jett", "Raze", "Skye", "Yoru", "Astra", "Kayo", "Chamber", "Neon", "Fade", "Harbor",
        "Gekko", "Deadlock", "Iso", "Clove", "Vyse", "Tejo", "Jun", "Luwi", "Ibara", "Mizu", "Kat", "Evi", "Sameti", 
        "Atlus", "Po", "Ling", "Pandi", "Bamboozle", "Kenny", "Shifu", "Pikachu", "Flambino", "Pancham", "Pangoro",
        "314159", "Salut", "Color", "Your", "Night"
    };
    
    /**
     * PErmet de générer le nom aléatoirement.
     * @return Le nom généré
     */
    public static String generate() {
        return NAMES[new Random().nextInt(NAMES.length)];
    }
}
