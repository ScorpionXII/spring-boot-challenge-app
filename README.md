# spring-boot-challenge-app
# Description

This challenge app filters a film list using meta-sql languaje following prefix notation to group boolean operations.

# Example

(and(and(price>0.99)(price<4))(or(category="Action")(category="Horror")))

The Controller is exposed at /film who looks for a parameter "q" with the filtering operation string

http://localhost:8080/film?q=(and(and(price>0.99)(price<4))(or(category="Action")(category="Horror")))

# Available Categories

Action Animation Children Classics Comedy Documentary Drama Family Foreign Games Horror Music New Sci-Fi Sports Travel

# Price

0.99 - 4.99
