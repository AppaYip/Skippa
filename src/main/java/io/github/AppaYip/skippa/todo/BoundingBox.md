# BoundingBox

This is mostly for me while I figure out how the heck bounding boxes work so I can make a decent Skript api.

This entire directory (including the files it contains) will be removed when I merge dev branch to main.

Things to note when messing with bounding boxes:

* Check if values passed in are finite - (Whether number args are bigger than max double value)
* Entity Bounding Boxes are cloned - Should only allow getting from property expression
* Bounding Boxes implement ConfigurationSerializable - We can save to persistant vars
* When using locations, values must be in the same world
* Bounding box coordinates will default to 0
* Most or all methods check for null args
* They can raytrace?

Things I don't want to do:

* Give every bounding box some unique id similar to SkBee's Bounds
* 
