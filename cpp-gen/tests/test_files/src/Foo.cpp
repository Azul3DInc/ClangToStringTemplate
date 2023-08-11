#pragma once

#include "../include/IFoo.h"
#include <iostream>

namespace fs
{
   class Foo : IFoo
   {
      Bar& returnBar(Bar &bar)
      {
         return bar;
      }
   };
}
