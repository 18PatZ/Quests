/*******************************************************************************************************

 * Continued by PikaMug (formerly HappyPikachu) with permission from _Blackvein_. All rights reserved.
 * 
 * THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN
 * NO EVENT SHALL THE REGENTS OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY
 * OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *******************************************************************************************************/

package me.blackvein.quests.util;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.blackvein.quests.Dependencies;
import me.blackvein.quests.Quest;
import me.clip.placeholderapi.PlaceholderAPI;
import net.citizensnpcs.api.npc.NPC;

public class ConfigUtil {
    
    /**
     * Checks whether items in a list are instances of a class<p>
     * 
     * Does NOT check whether list objects are null
     * 
     * @param list The list to check objects of
     * @param clazz The class to compare against
     * @return false if list is null or list object does not match
     */
    public static boolean checkList(List<?> list, Class<?> clazz) {
        if (list == null) {
            return false;
        }
        for (Object o : list) {
            if (clazz.isAssignableFrom(o.getClass()) == false) {
                return false;
            }
        }
        return true;
    }
    
    public static Location getLocation(String arg) {
        String[] info = arg.split(" ");
        
        if (info.length < 4) {
            return null;
        }
        
        StringBuilder sb = new StringBuilder();
        int index = 0;
        int xIndex = info.length -3;
        int yIndex = info.length -2;
        int zIndex = info.length -1;
        
        while (index < xIndex) {
            String s = info[index];
            if (index == 0) {
                sb.append(s);
            } else {
                sb.append(" " + s);
            }
            index++;
        }
        
        String world = sb.toString();
        
        double x;
        double y;
        double z;
        try {
            x = Double.parseDouble(info[xIndex]);
            y = Double.parseDouble(info[yIndex]);
            z = Double.parseDouble(info[zIndex]);
        } catch (Exception e) {
            Bukkit.getLogger().severe("Please inform Quests developer location was wrong for "
                    + world + " " + info[xIndex] + " " + info[yIndex] + " " + info[zIndex] + " ");
            return null;
        }
        if (Bukkit.getServer().getWorld(world) == null) {
            Bukkit.getLogger().severe("Quests could not locate world " + world + ", is it loaded?");
            return null;
        }
        Location finalLocation = new Location(Bukkit.getServer().getWorld(world), x, y, z);
        return finalLocation;
    }

    public static String getLocationInfo(Location loc) {
        return loc.getWorld().getName() + " " + loc.getX() + " " + loc.getY() + " " + loc.getZ();
    }
    
    public static String[] parseStringWithPossibleLineBreaks(String s, Quest quest, Player player) {
        String parsed = parseString(s);
        if (parsed.contains("<npc>")) {
            if (quest.getNpcStart() != null) {
                parsed = parsed.replace("<npc>", quest.getNpcStart().getName());
            } else {
                Bukkit.getLogger().warning(quest.getName() + " quest uses <npc> tag but doesn't have an NPC start set");
            }
        }
        if (Dependencies.placeholder != null && player != null) {
            parsed = PlaceholderAPI.setPlaceholders(player, parsed);
        }
        return parsed.split("\n");
    }
    
    public static String[] parseStringWithPossibleLineBreaks(String s, Quest quest) {
        String parsed = parseString(s);
        if (parsed.contains("<npc>")) {
            if (quest.getNpcStart() != null) {
                parsed = parsed.replace("<npc>", quest.getNpcStart().getName());
            } else {
                Bukkit.getLogger().warning(quest.getName() + " quest uses <npc> tag but doesn't have an NPC start set");
            }
        }
        return parsed.split("\n");
    }

    public static String[] parseStringWithPossibleLineBreaks(String s, NPC npc) {
        String parsed = parseString(s);
        if (parsed.contains("<npc>")) {
            parsed = parsed.replace("<npc>", npc.getName());
        }
        return parsed.split("\n");
    }
    
    public static String parseString(String s, Quest quest) {
        String parsed = parseString(s);
        if (parsed.contains("<npc>")) {
            if (quest.getNpcStart() != null) {
                parsed = parsed.replace("<npc>", quest.getNpcStart().getName());
            } else {
                Bukkit.getLogger().warning(quest.getName() + " quest uses <npc> tag but doesn't have an NPC start set");
            }
        }
        return parsed;
    }
    
    public static String parseString(String s, Quest quest, Player player) {
        String parsed = parseString(s, quest);
        if (Dependencies.placeholder != null && player != null) {
            parsed = PlaceholderAPI.setPlaceholders(player, parsed);
        }
        return parsed;
    }

    public static String parseString(String s, NPC npc) {
        String parsed = parseString(s);
        if (parsed.contains("<npc>")) {
            parsed = parsed.replace("<npc>", npc.getName());
        }
        return parsed;
    }
    
    public static String parseString(String s) {
        String parsed = s;
        parsed = parsed.replace("<black>", ChatColor.BLACK.toString());
        parsed = parsed.replace("<darkblue>", ChatColor.DARK_BLUE.toString());
        parsed = parsed.replace("<darkgreen>", ChatColor.DARK_GREEN.toString());
        parsed = parsed.replace("<darkaqua>", ChatColor.DARK_AQUA.toString());
        parsed = parsed.replace("<darkred>", ChatColor.DARK_RED.toString());
        parsed = parsed.replace("<purple>", ChatColor.DARK_PURPLE.toString());
        parsed = parsed.replace("<gold>", ChatColor.GOLD.toString());
        parsed = parsed.replace("<grey>", ChatColor.GRAY.toString());
        parsed = parsed.replace("<gray>", ChatColor.GRAY.toString());
        parsed = parsed.replace("<darkgrey>", ChatColor.DARK_GRAY.toString());
        parsed = parsed.replace("<darkgray>", ChatColor.DARK_GRAY.toString());
        parsed = parsed.replace("<blue>", ChatColor.BLUE.toString());
        parsed = parsed.replace("<green>", ChatColor.GREEN.toString());
        parsed = parsed.replace("<aqua>", ChatColor.AQUA.toString());
        parsed = parsed.replace("<red>", ChatColor.RED.toString());
        parsed = parsed.replace("<pink>", ChatColor.LIGHT_PURPLE.toString());
        parsed = parsed.replace("<yellow>", ChatColor.YELLOW.toString());
        parsed = parsed.replace("<white>", ChatColor.WHITE.toString());
        parsed = parsed.replace("<random>", ChatColor.MAGIC.toString());
        parsed = parsed.replace("<italic>", ChatColor.ITALIC.toString());
        parsed = parsed.replace("<bold>", ChatColor.BOLD.toString());
        parsed = parsed.replace("<underline>", ChatColor.UNDERLINE.toString());
        parsed = parsed.replace("<strike>", ChatColor.STRIKETHROUGH.toString());
        parsed = parsed.replace("<reset>", ChatColor.RESET.toString());
        parsed = parsed.replace("<br>", "\n");
        parsed = ChatColor.translateAlternateColorCodes('&', parsed);
        return parsed;
    }
}
