package com.makingdevs.diaspora

@Singleton
class Network {

  List commonFollowersFor(User user1, User user2) {
    def followersFor1 = user1.followers
    def followersFor2 = user2.followers
    followersFor1 - (followersFor1 - followersFor2)
  }

  def gridOfUsersAndFollowers(List<User> users) {

    def grid = [:]
    users.each { currentUser ->
      List followers = []
      users.each { anotherUser ->
        if(anotherUser.followers.any { u -> u.username == currentUser.username })
          followers << anotherUser
      }
      grid.put(currentUser.username, followers*.username)
    }
    grid
  }

  def graphvizForFollowers(user1, user2, commonFollowers) {
    commonFollowers.collect { follower ->
        "\"${user1.username}\" -> \"${follower.username}\";\n\"${user2.username}\" -> \"${follower.username}\";"
    }.join("\n")
  }

  def graphvizForFollowersOfFollowers(users) {
    users.collect { user ->
      user.value.collect { follower ->
        "\"${user.key}\" -> \"${follower}\";"
      }.join("\n")
    }.join("\n")
  }
}
