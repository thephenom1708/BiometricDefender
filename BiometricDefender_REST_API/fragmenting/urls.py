from django.conf.urls import url, include
from . import views

app_name = 'fragmenting'

urlpatterns = [
    url('^api/', include('fragmenting.api.urls'), name='api'),
    url('^makeFragments/$', views.makeFragments, name='makeFragments'),
    url('^recieveFragment/$',views.recieveFragment,name='recieveFragment'),
    url('^sendFragmentRequest/$', views.sendFragmentRequest, name="sendFragmentRequest"),
    url('^fragmentRequest/$', views.fragmentRequest, name="fragmentRequest"),
    #url('^api/', include('network.api.urls'), name='api'),
    url('^show/$', views.show, name="show")

]