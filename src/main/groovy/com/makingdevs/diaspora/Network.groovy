package com.makingdevs.diaspora

@Singleton
class Network {

  List commonFollowersFor(user1, user2) {
    def followersFor1 = user1.values().flatten()
    def followersFor2 = user2.values().flatten()
    followersFor1 - (followersFor1 - followersFor2)
  }

  def gridOfUsersAndFollowers(gridOfUsers) {
    def allUsers = gridOfUsers.collect { e -> e.collect { k, v  -> k} }.flatten()

    Map grid = [:]

    allUsers.each { user ->

      def followers = gridOfUsers.collect { userAndFollowers ->
        userAndFollowers.find { thisUser, followers ->
          followers.any { follower -> follower == user}
        }
      }.findAll { it }.collect { entries ->
        entries*.key
      }.flatten()

      grid.put(user, followers)
    }
    grid
  }

  def graphvizForFollowers(user1, user2) {
    def username1 = user1.keySet().flatten().first()
    def username2 = user2.keySet().flatten().first()
    def commonFollowersFor = commonFollowersFor(user1, user2)
    commonFollowersFor.collect { follower ->
        "${username1} -> ${follower};\n${username2} -> ${follower};"
    }.join("\n")
  }

  def graphvizForFollowersOfFollowers(Map gridOfUsers) {
    gridOfUsers.collect { user, followers ->
      followers.collect { follower ->
        "${user} -> ${follower};"
      }.join("\n")
    }.join("\n")
  }
}
