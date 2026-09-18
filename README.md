# Spigot / Paper Plugin Development Sandbox

A workspace repo where I build practice plugins, test ideas, and learn the Bukkit / Spigot 1.20+ API on a Paper server.

## What I've Learned & Built

- **Commands & Tab Completion (`/set-pvp`, `/heal` in `spigot-example`)**
  - My first Spigot plugin!
  - Built custom `CommandExecutor` setups with configuration checks.
  - Added dynamic auto-complete logic using `TabCompleter` to check [ true | false ].

- **Event Listeners & Item Meta (`blockprotect`, `iteminspector`)**
  - Intercepted player clicks and block interactions using `PlayerInteractEvent`, `BlockBreakEvent`, and `BlockPlaceEvent`.
  - Parsed `ItemMeta` attributes (display names, custom lore, stack amounts) and tuned execution order using Spigot's `EventPriority`.

- **Locations, Vectors & Distance Calculations (`playerfinder`)**
  - Managed 3D coordinates ($X, Y, Z$) and orientation ($Yaw, Pitch$).
  - Extracted 3D directional vectors between player locations and target blocks using relative math ($\Delta X, \Delta Y, \Delta Z$) alongside Spigot's built-in `.distance()` and `.toVector()` utilities.

---
*More experiments soon*
