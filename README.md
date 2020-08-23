# DoomedFabric

A place to collect all my horrible "library" ideas and even worse implementations. ~~Truth~~ Jokes aside, this is going to be a collection of Codesnippets that take some of the tedium out of Modding. It also contains temporary solutions to problems that exist when new versions come out, such as the changes to Biomes in 1.16.2 that completely broke oregen for a while (not sure if it's still broken, I'm just too lazy to update the description everytime some minor updates happen)

## Usage

To use this Library in your own mods (even though I'm not sure why you'd want to) add

```gradle
repositories {
    maven {
        url = 'https://raw.githubusercontent.com/MessiahOfDoom/Maven-Repo/master'
    }
}
```
as a maven repository and 

```gradle

dependencies {
	
	//[...] all your other stuff here
	modImplementation "de.schneider-oliver:DoomedFabric:1.1.0"
	
}

```
as a dependency, replacing `1.1.0` with whatever the current version is. 
