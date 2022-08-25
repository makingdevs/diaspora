package com.makingdevs.diaspora

@Singleton
class Network {

  List commonFollowers(followersForUser1, followersForUser2) {
    def followersFor1 = followersForUser1.values().flatten()
    def followersFor2 = followersForUser2.values().flatten()
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

  def graphvizForFollowers(followersForUser1, followersForUser2) {
    def user1 = followersForUser1.keySet().flatten().first()
    def user2 = followersForUser2.keySet().flatten().first()
    def commonFollowers = commonFollowers(followersForUser1, followersForUser2)
    commonFollowers.collect { follower ->
        "${user1} -> ${follower};\n${user2} -> ${follower};"
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
