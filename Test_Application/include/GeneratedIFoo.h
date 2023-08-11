#pragma once

#include "IFoo.h"
#include <vector>

namespace ns
{
    struct MockIFoo : public IFoo
    {
        bool run_isCalled { false };
        unsigned run_callCount { 0 };

        void run() override
        {
            run_isCalled = true;
            ++run_callCount;

        }

        bool isPositive_isCalled { false };
        unsigned isPositive_callCount { 0 };
        bool isPositive_return;

        bool isPositive() override
        {
            isPositive_isCalled = true;
            ++isPositive_callCount;

            return isPositive_return;
        }

        bool getNumber_isCalled { false };
        unsigned getNumber_callCount { 0 };
        int getNumber_return;

        int getNumber() override
        {
            getNumber_isCalled = true;
            ++getNumber_callCount;

            return getNumber_return;
        }

        bool sendNumber_isCalled { false };
        unsigned sendNumber_callCount { 0 };
        std::vector<int> sendNumber_param_1;

        void sendNumber(int num) override
        {
            sendNumber_isCalled = true;
            ++sendNumber_callCount;

            sendNumber_param_1.push_back(num);

        }

        bool getString_isCalled { false };
        unsigned getString_callCount { 0 };
        std::string getString_return;

        std::string getString() override
        {
            getString_isCalled = true;
            ++getString_callCount;

            return getString_return;
        }
    };
} // namespace ns
