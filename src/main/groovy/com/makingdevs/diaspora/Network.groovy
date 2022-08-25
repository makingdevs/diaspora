package com.makingdevs.diaspora

@Singleton
class Network {

  List commonFollowers(followersForUser1, followersForUser2) {
    followersForUser1 - (followersForUser1 - followersForUser2)
  }

  def gridOfUsersAndFollowers(gridOfUsers) {
    def allUsers = gridOfUsers.collect { e -> e.collect { k, v  -> k} }.flatten()

    allUsers.collect { user ->
      gridOfUsers.collect { userAndFollowers ->
        userAndFollowers.find { thisUser, followers ->
          followers.any { follower -> follower == user}
        }
      }.findAll { it }.collect { entries ->
        entries.collect { entry -> entry.key }
      }.flatten()
    }
  }
}
