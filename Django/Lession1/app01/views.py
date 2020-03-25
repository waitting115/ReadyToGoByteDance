from django.http import HttpResponse
from django.shortcuts import render


# Create your views here.
def hello(request):
    return HttpResponse('Hello Django');


def haha(request):
    return HttpResponse('Hello Haha');

# def index(request):
#     return render(index,)