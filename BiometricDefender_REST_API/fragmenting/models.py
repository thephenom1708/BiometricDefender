from django.db import models
import secrets

class Peer(models.Model):
    id = models.CharField(max_length=100, primary_key=True)
    name = models.CharField(max_length=255)
    address = models.CharField(max_length=255, unique=True)

    def __str__(self):
        return self.name + "@" + self.address

    def createNewPeer(self, name, address):
        self.id = secrets.token_hex(10)
        self.name = name
        self.address = address
        return self

class Fragment(models.Model):
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    id = models.CharField(max_length=100, primary_key=True)
    username = models.CharField(max_length=255)
    fragment_data = models.TextField()

    def __str__(self):
        return self.id + "@" + self.username

    def createNewFragment(self, username, fragment_data):
        self.id = secrets.token_hex(10)
        self.username = username
        self.fragment_data = fragment_data


