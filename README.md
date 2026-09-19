# Spigot / Paper Plugin Development Sandbox

A workspace repo where I build practice plugins, test ideas, and learn the Bukkit / Spigot 1.20+ API on a Paper server.
*please don't use any of the plugins as they're not meant for production and have many debugging or unexpected behavior*

## What I've Learned & Built

- **Commands & Tab Completion (`/set-pvp`, `/heal` in `spigot-example`)**
    - My first Spigot plugin!
    - Built custom `CommandExecutor` setups with configuration checks.
    - Added dynamic auto-complete logic using `TabCompleter` to check [ true | false ].

- **Event Listeners & Item Meta (`blockprotect`, `iteminspector`)**
    - Intercepted player clicks and block interactions using `PlayerInteractEvent`, `BlockBreakEvent`, and `BlockPlaceEvent`.

- **Locations, Vectors & Distance Calculations (`playerfinder`)**
    - Managed 3D coordinates and orientation.
    - Extracted 3D directional vectors between player locations and target blocks using Spigot's built-in `.distance()` and `.toVector()` utils.

- **Spawning of Entities, Items & Block Manipulation (`luckyblocks`)**
    - Modified blocks and made entities and items spawn surrounding this block, changing the behavior of a block to be a completly different one
    - World manipulation by modifying velocity of a player
    - Custom entities! With new attribute values that make it stronger.
    - (You can use a feather to right click any block to transform it into a Diamond Ore block and right click with an empty hand to see yourself!)

- **Task Scheduling & Server Broadcast (`servermessages`)**
    - Handled task scheduling for specific repeated events or delays using `BukkitRunnable`
    - Handled asynchronous tasks with `runTaskAsynchronously` to process CPU heavy without interrupting the server
    - An annoying server message that appears every 10 seconds to remind you to vote.

---
*More experiments soon*
