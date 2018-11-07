
# Movie Recommender System
This program runs in the console (for now).
It asks the user to pick a genre of movie, then provides them with 10 (or less) movies from that genre to rate. It creates a user profile based on these ratings and then suggests  movies for them to watch next. These suggestions are created by comparing their ratings to other users with similar ratings, giving users more like them a greater weight in the recommendation.

##Classes in this project:

### EfficientRaters
#### Is a:
* Rater
#### Has a:
* ID
* HashMap of userID and their Ratings

### Filter
#### Is a:
* Interface
#### Has a:
* Satisfies method which returns true or false

### FilterAllTrue
#### Is a:
* Filter
#### Actions:
* Filters nothing out (all return true)

### FilterByDirector
#### Is a:
* Filter
#### Actions:
* Filters out by given director(s)


### FilterByGenre
#### Is a:
* Filter
#### Actions:
* Filters out by given genre


### FilterByMinutes
#### Is a:
* Filter
#### Actions:
* Filters out by given length of movie in minutes

### FilterByMultipleCriteria
#### Is a:
* Filter
#### Actions:
* Uses one or more filters to return true or false

### FilterByYearSince
#### Is a:
* Filter
#### Actions:
* Filters out movies to only return movies released since a given year

### Movie
#### Has (a/an):
* ID
* Title
* Year
* Genre
* Director
* Country
* Minutes
* Poster (URL)

### MovieDatabase
#### Has (a/an):
* Mapping of Movie IDs to Movie Objects

### MovieRecommendationRunner
* Holds the logic and methods for the program to run in the console

### MovieRecommender
* Contains main method

### OldMethods
* Contains methods written for Duke University's assignments which currently do not fit into any other section of the project.

### Rater
#### Is a
* Interface

### RaterDatabase
#### Has a/an:
H* ashMap of RaterIDs to Rater objects

### Ratings
#### Has a/an:
* ID
* Rating value


### SimilarityScores
* Allows us to find a similarity score between two users.
