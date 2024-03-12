# ChatIgnorer
This is a small Fabric client side mod to ignore specific players and or text patterns in chat. This is specifically tailerd to the minecraft server <http://back2basics.gg/>.

## Usage
For simple player ignoring, run `/ignore \<playernick\>` to (un)ignore.
This can also be done via direct config access. You can see who you ignored with `/listignore`.

For more advanced pattern matching, you can add your own custom regexs in the config. Do be aware that they will be deleted if they cannot compile, but the old regex will be logged.

## Limitations
Due to the way chat is implemented on b2b, I can only provide a "best effort" ignore. This implementation cannot handle people changing their nicknames.

