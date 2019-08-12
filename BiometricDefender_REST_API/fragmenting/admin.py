from django.contrib import admin
from .models import Peer, Fragment

admin.site.register(Peer)


class FragmentAdmin(admin.ModelAdmin):
    readonly_fields = ['fragment_data', 'username', 'id']


admin.site.register(Fragment, FragmentAdmin)