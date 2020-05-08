from django.db import models

# Create your models here.

# 创建模型类
class  Meishi(models.Model):
    id = models.AutoField(primary_key=True)  # 该字段可以不写，它会自动补全
    food_name = models.CharField(max_length=30) # 设置食物名称
    food_author = models.CharField(max_length=8) # 设置食物制作人
    food_money = models.FloatField()  # 设置食物价格
    food_star = models.CharField(max_length=10,default='普通') # 设置食物美味程度

    def __str__(self):  # 重写直接输出类的方法
        return "<Meishi:{id=%s,food_name=%s,food_author=%s,food_money=%s,food_star=%s}>"\
               %(self.id,self.food_name,self.food_author,self.food_money,self.food_star)
