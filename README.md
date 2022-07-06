# voronoi_tesselations
# About
A Voronoi tessellation is a partition of a plane into regions close to each of a given set of objects. In the simplest case, these objects are just finitely many points in the plane (called seeds, sites, or generators). For each seed there is a corresponding region, called a Voronoi cell, consisting of all points of the plane closer to that seed than to any other.

# Applications
There are numerous other applications of Voronoi diagrams. These include network analysis, computer graphics, medical diagnostics, astrophysics, hydrology, robotics and computational fluid dynamics.

# Usage
* Input a csv file of data points(latitude and longitude) for objects
* Outputs an image consisting of a formation of polygons around the objects

# Examples (Screenshots)
## Aviation
In aviation, Voronoi diagrams are superimposed on plotting charts to identify the nearest airfield for in-flight diversion (see ETOPS), as an aircraft progresses through its flight plan
https://github.com/LyshaJaufar/voronoi_tesselations/blob/master/src/images/airports.png?raw=true

## Fastfood
Assume you are planning to walk to a restaurant to have lunch. There are numerous restaurants nearby. Which one should you go to (disregarding food quality and only considering convenience)? The closest restaurant, right!
The point within a Voronoi cell is the closest restaurant for everyone living in that region(Voronoi cell)
https://github.com/LyshaJaufar/voronoi_tesselations/blob/master/src/images/michelin-restaurants.png?raw=true

## Hydrology
It is used in meteorology and engineering hydrology to find the weights for precipitation data of stations over an area (watershed). The points generating the polygons are the various station that record precipitation data. 
https://github.com/LyshaJaufar/voronoi_tesselations/blob/master/src/images/weather-stations.png?raw=true

# Meteorite Landings
https://github.com/LyshaJaufar/voronoi_tesselations/blob/master/src/images/meteorite-landings.png?raw=true

# Earthquakes
https://github.com/LyshaJaufar/voronoi_tesselations/blob/master/src/images/significant-earthquakes.png?raw=true
