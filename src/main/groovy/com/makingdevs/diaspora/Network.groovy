package com.makingdevs.diaspora

@Singleton
class Network {

  List commonFollowers(followersForUser1, followersForUser2) {
    followersForUser1 - (followersForUser1 - followersForUser2)
  }
}
