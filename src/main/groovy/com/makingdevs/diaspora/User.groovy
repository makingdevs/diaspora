package com.makingdevs.diaspora

@groovy.transform.Canonical
class User {

  Integer id
  String username
  Integer followersSize = 0
  List<User> followers = []

}
