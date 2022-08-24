package com.makingdevs.diaspora

import org.junit.Test

class NetworkTest {

  def network = Network.instance

  @Test
  void commonFollorwersTest(){
    def user1Followers = ["user2", "user3", "user4"]
    def user2Followers = ["user3", "user4", "user5"]

    assert ["user3", "user4"] == network.commonFollowers(user1Followers, user2Followers)
  }
}
